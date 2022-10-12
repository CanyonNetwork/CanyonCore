package net.canyon.utils.strings


interface AnimatableString {

    fun current(): String?
    fun last(): String
    fun next(): String

}

open class FrameAnimatedString(private val frames: MutableList<String>) : AnimatableString {
    var currentFrame = -1

    constructor() : this(mutableListOf())
    constructor(vararg frames: String) : this(frames.toMutableList()) {}

    fun addFrame(string: String) {
        frames.add(string)
    }

    fun setFrame(frame: Int, string: String) {
        frames[frame] = string
    }

    fun removeFrame(string: String) {
        frames.remove(string)
    }

    val totalLength: Int
        get() = frames.size

    fun getString(frame: Int): String {
        return frames[frame]
    }

    override fun current(): String? {
        return if (currentFrame == -1) null else frames[currentFrame]
    }

    override operator fun next(): String {
        currentFrame++
        if (currentFrame == frames.size) currentFrame = 0
        return frames[currentFrame]
    }

    override fun last(): String {
        currentFrame--
        if (currentFrame == -1) currentFrame = frames.size - 1
        return frames[currentFrame]
    }
}