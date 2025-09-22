package com.romnan.chillax.data.source

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import com.romnan.chillax.R
import com.romnan.chillax.domain.constant.PlayerConstants
import com.romnan.chillax.domain.model.Category
import com.romnan.chillax.domain.model.Mood
import com.romnan.chillax.domain.model.Sound
import com.romnan.chillax.domain.model.UIText

class AppDataSource(
    private val appContext: Context,
) {

    val sounds: List<Sound> = SoundData.entries.map { soundData: SoundData ->
        Sound(
            id = soundData.id,
            readableName = soundData.readableName,
            iconResId = soundData.iconResId,
            audioResId = soundData.audioResId,
        )
    }

    val presetMoods: List<Mood> = MoodData.entries.map { moodData: MoodData ->
        val imageUri = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(appContext.packageName)
            .appendPath(moodData.imageResId.toString())
            .build()
            .toString()

        Mood(
            id = moodData.id,
            readableName = moodData.readableName,
            imageUri = imageUri,
            soundIdToVolume = moodData.sounds.associate { soundData: SoundData ->
                soundData.id to PlayerConstants.DEFAULT_SOUND_VOLUME
            },
        )
    }

    val categories: List<Category> = CategoryData.entries.map { categoryData: CategoryData ->
        Category(
            id = categoryData.id,
            readableName = categoryData.readableName,
            description = categoryData.description,
            soundIds = categoryData.sounds.map { soundData: SoundData -> soundData.id },
        )
    }

    val moodImageUris: Set<String> = listOf(
        R.raw.mood_alpha,
        R.raw.mood_beta,
        R.raw.mood_delta,
        R.raw.mood_gammaa,
        R.raw.mood_theta,
        R.raw.mood_san_,
    ).map { resId: Int ->
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(appContext.packageName)
            .appendPath(resId.toString())
            .build()
            .toString()
    }.toSet()
}

private enum class MoodData(
    val readableName: UIText,
    val imageResId: Int,
    val sounds: List<SoundData>,
) {
    Alpha(
        readableName = UIText.StringResource(R.string.mood_alpha),
        imageResId = R.raw.mood_alpha,
        sounds = listOf(
            SoundData.Alpha,
            SoundData.Alphaa,

            ),
    ),
    Beta(
        readableName = UIText.StringResource(R.string.mood_beta),
        imageResId = R.raw.mood_beta,
        sounds = listOf(
            SoundData.Beta,
            SoundData.Betaa,
        ),
    ),
    Delta(
        readableName = UIText.StringResource(R.string.mood_delta),
        imageResId = R.raw.mood_delta,
        sounds = listOf(
            SoundData.Delta,
            SoundData.Deltaa,
        ),
    ),
    Gamma(
        readableName = UIText.StringResource(R.string.mood_gamma),
        imageResId = R.raw.mood_gammaa,
        sounds = listOf(
            SoundData.Gamma,
            SoundData.Gammaa,
        ),
    ),
    Theta(
        readableName = UIText.StringResource(R.string.mood_theta),
        imageResId = R.raw.mood_theta,
        sounds = listOf(
            SoundData.Theta,
            SoundData.Thetaa,
        ),
    ), ;

    val id: String
        get() = name
}

private enum class CategoryData(
    val readableName: UIText,
    val description: UIText,
    val sounds: List<SoundData>,
) {
    Alpha(
        readableName = UIText.StringResource(R.string.cat_name_alpha),
        description = UIText.StringResource(R.string.cat_desc_rain),
        sounds = listOf(
            SoundData.Alpha,
            SoundData.Alphaa,

            ),
    ),
    Beta(
        readableName = UIText.StringResource(R.string.cat_name_beta),
        description = UIText.StringResource(R.string.cat_desc_nature),
        sounds = listOf(
            SoundData.Beta,
            SoundData.Betaa,
        ),
    ),
    Delta(
        readableName = UIText.StringResource(R.string.cat_name_delta),
        description = UIText.StringResource(R.string.cat_desc_animals),
        sounds = listOf(
            SoundData.Delta,
            SoundData.Deltaa,
        ),
    ),
    Gamma(
        readableName = UIText.StringResource(R.string.cat_name_gamma),
        description = UIText.StringResource(R.string.cat_desc_room),
        sounds = listOf(
            SoundData.Gamma,
            SoundData.Gammaa,
        ),
    ),
    Theta(
        readableName = UIText.StringResource(R.string.cat_name_theta),
        description = UIText.StringResource(R.string.cat_desc_city),
        sounds = listOf(
            SoundData.Theta,
            SoundData.Thetaa,
        ),
    ), ;

    val id: String
        get() = name
}

private enum class SoundData(
    val readableName: UIText,
    val iconResId: Int,
    val audioResId: Int,
) {
    Alpha(
        readableName = UIText.StringResource(R.string.sound_alpha),
        iconResId = R.drawable.calmnes,
        audioResId = R.raw.sound_alpha,
    ),
    Theta(
        readableName = UIText.StringResource(R.string.sound_theta),
        iconResId = R.drawable.kreatip,
        audioResId = R.raw.sound_theta,
    ),
    Alphaa(
        readableName = UIText.StringResource(R.string.sound_alphaa),
        iconResId = R.drawable.sleep,
        audioResId = R.raw.sound_alphaa,
    ),
    Gamma(
        readableName = UIText.StringResource(R.string.sound_gamma),
        iconResId = R.drawable.informan,
        audioResId = R.raw.sound_gamma,
    ),

    Beta(
        readableName = UIText.StringResource(R.string.sound_beta),
        iconResId = R.drawable.work,
        audioResId = R.raw.sound_beta,
    ),
    Betaa(
        readableName = UIText.StringResource(R.string.sound_betaa),
        iconResId = R.drawable.study,
        audioResId = R.raw.sound_betaa,
    ),
    Delta(
        readableName = UIText.StringResource(R.string.sound_delta),
        iconResId = R.drawable.mental,
        audioResId = R.raw.sound_delta,
    ),
    Thetaa(
        readableName = UIText.StringResource(R.string.sound_thetaa),
        iconResId = R.drawable.light,
        audioResId = R.raw.sound_thetaa,
    ),
    Gammaa(
        readableName = UIText.StringResource(R.string.sound_gammaa),
        iconResId = R.drawable.intens,
        audioResId = R.raw.sound_gammaa,
    ),
    Deltaa(
        readableName = UIText.StringResource(R.string.sound_deltaa),
        iconResId = R.drawable.ic_sound_hearbeat,
        audioResId = R.raw.sound_deltaa,
    ), ;

    val id: String
        get() = name
}