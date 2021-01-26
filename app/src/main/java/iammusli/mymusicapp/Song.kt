package iammusli.mymusicapp


class Song(){
    private var SongName = "Nesto ne valja"
    private var SongLink = "Nesto ne valja"
    private var SongImage = "Nesto ne valja"

    constructor(SongName: String, SongLink: String, SongImage: String) : this() {
        this.SongName = SongName
        this.SongLink = SongLink
        this.SongImage = SongImage
    }

    fun setSongName (Name: String){
        this.SongName = Name
    }
    fun setSongLink (Link: String){
        this.SongLink = Link
    }
    fun setSongImage (Image: String){
        this.SongImage = Image
    }
    fun getSongName(): String {
        return SongName
    }
    fun getSongLink(): String {
        return SongLink
    }
    fun getSongImage(): String {
        return SongImage
    }
}