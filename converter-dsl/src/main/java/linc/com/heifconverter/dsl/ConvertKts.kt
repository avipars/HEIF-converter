package linc.com.heifconverter.dsl

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import linc.com.heifconverter.HeifConverter
import java.io.File
import java.io.InputStream

/**
 * A method for converting a HEIC from [File] to a [Bitmap].
 *
 * Example:
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
 * @param[context] [Context] reference to initialize [HeifConverter].
 * @param[file] Input HEIC [File].
 * @param[options] Custom [HeifConverter.Options] for [HeifConverter].
 * @return Result map containing the [Bitmap] and a path to the saved bitmap.
 * @see HeifConverter.convertBlocking for more info.
 * @see HeifConverterDsl for all available options.
 */
public suspend fun HeifConverter.Companion.convert(
    context: Context,
    file: File,
    options: HeifConverter.Options,
): Map<String, Any?> = create(context, file, options).convert()

/**
 * A method for converting a HEIC from [InputStream] to a [Bitmap].
 *
 * Example:
 *
 * ```
 * val options = HeifConverter.Options.build {
 *     saveResultImage = true
 *     outputQuality(50)
 *     outputDirectory(context.cacheDir)
 * }
 *
 * File(context.cacheDir, "image.heic").inputStream().use { inputStream ->
 *     val result = HeifConverter.convert(context, inputStream, options)
 * }
 * ```
 *
 * @param[context] [Context] reference to initialize [HeifConverter].
 * @param[inputStream] Input HEIC as an [InputStream].
 * @param[options] Custom [HeifConverter.Options] for [HeifConverter].
 * @return Result map containing the [Bitmap] and a path to the saved bitmap.
 * @see HeifConverter.convertBlocking for more info.
 * @see HeifConverterDsl for all available options.
 */
public suspend fun HeifConverter.Companion.convert(
    context: Context,
    inputStream: InputStream,
    options: HeifConverter.Options,
): Map<String, Any?> = create(context, inputStream, options).convert()

/**
 * A method for converting a HEIC from [DrawableRes] resource ID [Int] to a [Bitmap].
 *
 * Example:
 *
 * ```
 * val options = HeifConverter.Options.build {
 *     saveResultImage = true
 *     outputQuality(50)
 *     outputDirectory(context.cacheDir)
 * }
 *
 * val result = HeifConverter.convert(context, R.drawable.heic_image, options)
 * ```
 *
 * @param[context] [Context] reference to initialize [HeifConverter].
 * @param[resId] Input HEIC resource id [Int].
 * @param[options] Custom [HeifConverter.Options] for [HeifConverter].
 * @return Result map containing the [Bitmap] and a path to the saved bitmap.
 * @see HeifConverter.convertBlocking for more info.
 * @see HeifConverterDsl for all available options.
 */
public suspend fun HeifConverter.Companion.convert(
    context: Context,
    @DrawableRes resId: Int,
    options: HeifConverter.Options,
): Map<String, Any?> = create(context, resId, options).convert()

/**
 * A method for converting a HEIC from a [String] image URL to a [Bitmap].
 *
 * First [imageUrl] will be downloaded then converted to a [Bitmap].
 *
 * Example:
 *
 * ```
 * val options = HeifConverter.Options.build {
 *     saveResultImage = true
 *     outputQuality(50)
 *     outputDirectory(context.cacheDir)
 * }
 *
 * val result = HeifConverter.convert(context, "https://sample.com/image.heic", options)
 * ```
 *
 * @param[context] [Context] reference to initialize [HeifConverter].
 * @param[imageUrl] A URL pointing to a HEIC file.
 * @param[options] Custom [HeifConverter.Options] for [HeifConverter].
 * @return Result map containing the [Bitmap] and a path to the saved bitmap.
 * @see HeifConverter.convertBlocking for more info.
 * @see HeifConverterDsl for all available options.
 */
public suspend fun HeifConverter.Companion.convert(
    context: Context,
    imageUrl: String,
    options: HeifConverter.Options,
): Map<String, Any?> = create(context, imageUrl, options).convert()

/**
 * A method for converting a [ByteArray] HEIC to a [Bitmap].
 *
 * Example:
 *
 * ```
 * val options = HeifConverter.Options.build {
 *     saveResultImage = true
 *     outputQuality(50)
 *     outputDirectory(context.cacheDir)
 * }
 *
 * val heicByteArray = File(context.cacheDir, "image.heic").readBytes()
 * val result = HeifConverter.convert(context, heicByteArray, options)
 * ```
 *
 * @param[context] [Context] reference to initialize [HeifConverter].
 * @param[byteArray] Input HEIC data as a [ByteArray].
 * @param[options] Custom [HeifConverter.Options] for [HeifConverter].
 * @return Result map containing the [Bitmap] and a path to the saved bitmap.
 * @see HeifConverter.convertBlocking for more info.
 * @see HeifConverterDsl for all available options.
 */
public suspend fun HeifConverter.Companion.convert(
    context: Context,
    byteArray: ByteArray,
    options: HeifConverter.Options,
): Map<String, Any?> = create(context, byteArray, options).convert()

/**
 * A method for converting a [HeifConverter.Input] HEIC to a [Bitmap].
 *
 * Example:
 *
 * ```
 * val options = HeifConverter.Options.build {
 *     saveResultImage = true
 *     outputQuality(50)
 *     outputDirectory(context.cacheDir)
 * }
 *
 * val input = HeifConverter.Input.Url("https://sample.com/image.heic")
 * val result = HeifConverter.convert(context, input, options)
 * ```
 *
 * @param[context] [Context] reference to initialize [HeifConverter].
 * @param[input] Input HEIC data a instance of [HeifConverter.Input].
 * @param[options] Custom [HeifConverter.Options] for [HeifConverter].
 * @return Result map containing the [Bitmap] and a path to the saved bitmap.
 * @see HeifConverter.convertBlocking for more info.
 * @see HeifConverterDsl for all available options.
 */
public suspend fun HeifConverter.Companion.convert(
    context: Context,
    input: HeifConverter.Input,
    options: HeifConverter.Options,
): Map<String, Any?> = create(context, input, options).convert()