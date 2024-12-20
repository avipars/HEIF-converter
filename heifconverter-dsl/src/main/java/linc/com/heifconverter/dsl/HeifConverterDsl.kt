package linc.com.heifconverter.dsl

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.IntRange
import linc.com.heifconverter.HeifConverter
import linc.com.heifconverter.HeifConverter.Format
import linc.com.heifconverter.HeifConverter.Input
import linc.com.heifconverter.decoder.HeicDecoder
import java.io.File

/**
 * DSL builder for generating a [HeifConverter] or [HeifConverter.Options].
 *
 * Create a converter for a URL:
 *
 * ```
 * val converter = HeifConverter.create(context, "https://sample.com/image.heic") {
 *     saveResultImage = true
 *     outputDirectory = File(context.cacheDir)
 * }
 *
 * val result = converter.convert()
 * ```
 *
 * Convert a [File] reference:
 *
 * ```
 * val heicFile = File(context.cacheDir, "image.heic")
 * val result = HeifConverter.convert(context, heicFile) {
 *     saveResultImage = true
 *     outputName = "image"
 *     outputDirectory = File(context.cacheDir)
 *     outputFormat = HeifConverter.Format.JPEG
 * }
 * ```
 *
 * Build a [HeifConverter.Options] instance:
 *
 * ```
 * val options = HeifConverter.Options.build {
 *     saveResultImage = true
 *     outputQuality(50)
 *     outputDirectory(context.cacheDir)
 * }
 *
 * val heicFile = File(context.cacheDir, "image.heic")
 * val result = HeifConverter.convert(context, heicFile, options)
 * ```
 *
 * Using a custom [HeicDecoder]:
 *
 * ```
 * val heicFile = File(context.cacheDir, "image.heic")
 * val result = HeifConverter.convert(context, heicFile) {
 *     customDecoder = object : HeicDecoder() {
 *         // implementation
 *     }
 * }
 * ```
 *
 * Or using `decoder-glide`
 *
 * ```
 *  val heicFile = File(context.cacheDir, "image.heic")
 * val result = HeifConverter.convert(context, heicFile) {
 *     customDecoder = GlideHeicDecoder(context)
 * }
 * ```
 */
public interface HeifConverterDsl {

    /**
     * Set to `true` to save the converted [Bitmap] to [HeifConverter.Options.pathToSaveDirectory].
     *
     * @see HeifConverter.saveResultImage
     */
    public var saveResultImage: Boolean

    /**
     * Set to `true` to save the converted [Bitmap] to [HeifConverter.Options.pathToSaveDirectory].
     *
     * @see HeifConverter.saveResultImage
     */
    public fun saveResultImage(saveResultImage: Boolean)

    /**
     * Set output image for when saving the result [Bitmap] to the disk. Default: [Format.JPEG].
     *
     * @see HeifConverter.withOutputFormat
     */
    public var outputFormat: Format

    /**
     * Set output image for when saving the result [Bitmap] to the disk. Default: [Format.JPEG].
     *
     * @see HeifConverter.withOutputFormat
     */
    public fun outputFormat(format: Format)

    /**
     * Set the quality of the saved file. Default: 100
     *
     * @see HeifConverter.withOutputQuality
     */
    public fun outputQuality(@IntRange(from = 0, to = 100) quality: Int)

    /**
     * Set the filename of the saved file. Default: A random UUID
     *
     * @see HeifConverter.saveFileWithName
     */
    public var outputName: String

    /**
     * Set the filename of the saved file. Default: A random UUID
     *
     * @see HeifConverter.saveFileWithName
     */
    public fun outputName(fileName: String)

    /**
     * Set the output directory of the saved file using a [File] reference. Default: DCIM folder
     *
     * @see HeifConverter.saveToDirectory
     */
    public var outputDirectory: File?

    /**
     * Set the output directory of the saved file using a [File] reference. Default: DCIM folder
     *
     * @see HeifConverter.saveToDirectory
     */
    public fun outputDirectory(directory: File)

    /**
     * Set the output directory of the saved file using an absolute path reference.
     *
     * Default: DCIM folder
     *
     * @see HeifConverter.saveToDirectory
     */
    public fun outputDirectory(path: String) {
        outputDirectory(File(path))
    }

    /**
     * Use a default directory for saving the bitmap. See [HeifConverter.Options.defaultOutputPath].
     *
     * @see HeifConverter.Options.pathToSaveDirectory
     */
    public fun useDefaultOutputPath(context: Context)

    /**
     * A custom decoder for converting a HEIC [Input] to a [Bitmap]. Default: [HeicDecoder.Default]
     *
     * @see HeifConverter.withCustomDecoder
     */
    public var customDecoder: HeicDecoder?

    /**
     * A custom decoder for converting a HEIC [Input] to a [Bitmap]. Default: [HeicDecoder.Default]
     *
     * @see HeifConverter.withCustomDecoder
     */
    public fun customDecoder(decoder: HeicDecoder?)

    /**
     * A custom [HeicDecoder.UrlLoader] for downloading the URL from [HeifConverter.fromUrl].
     *
     * @see HeifConverter.withUrlLoader
     */
    public var urlLoader: HeicDecoder.UrlLoader?

    /**
     * A custom [HeicDecoder.UrlLoader] for downloading the URL for [Input.Url].
     *
     * @see HeifConverter.withUrlLoader
     */
    public fun urlLoader(urlLoader: HeicDecoder.UrlLoader?)
}

/**
 * Internal implementation of [HeifConverterDsl].
 *
 * @property[options] [HeifConverter.Options] object to initialize the builder.
 * @constructor Builder with defaults.
 */
internal class InternalHeifConverterDsl(
    internal var options: HeifConverter.Options = HeifConverter.Options(),
) : HeifConverterDsl {

    override var saveResultImage: Boolean
        get() = options.saveResultImage
        set(value) {
            options = options.copy(saveResultImage = value)
        }

    override fun saveResultImage(saveResultImage: Boolean) {
        this.saveResultImage = saveResultImage
    }

    override var outputFormat: Format
        get() = options.outputFormat
        set(value) {
            options = options.copy(outputFormat = value)
        }

    override fun outputFormat(format: Format) {
        this.outputFormat = format
    }

    override fun outputQuality(quality: Int) {
        options = options.copy(outputQuality = quality.coerceIn(0..100))
    }

    override var outputName: String
        get() = options.outputFileName
        set(value) {
            options = options.copy(outputFileName = value)
        }

    override fun outputName(fileName: String) {
        this.outputName = fileName
    }

    override var outputDirectory: File?
        get() = options.pathToSaveDirectory
        set(value) {
            options = options.copy(pathToSaveDirectory = value)
        }

    override fun outputDirectory(directory: File) {
        this.outputDirectory = directory
    }

    override fun useDefaultOutputPath(context: Context) {
        val path = HeifConverter.Options.defaultOutputPath(context)
        options = options.copy(pathToSaveDirectory = path)
    }

    override var customDecoder: HeicDecoder?
        get() = options.decoder
        set(value) {
            options = options.copy(decoder = value)
        }

    override fun customDecoder(decoder: HeicDecoder?) {
        this.customDecoder = decoder
    }

    override var urlLoader: HeicDecoder.UrlLoader?
        get() = options.urlLoader
        set(value) {
            options = options.copy(urlLoader = value)
        }

    override fun urlLoader(urlLoader: HeicDecoder.UrlLoader?) {
        this.urlLoader = urlLoader
    }

    fun build(context: Context) = HeifConverter.create(context, options)
}

