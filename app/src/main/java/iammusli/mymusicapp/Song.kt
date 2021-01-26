package iammusli.mymusicapp

class Song(){
    private var SongName = "Nesto ne valja"
    private var SongLink = "Nesto ne valja"

    constructor(SongName: String, SongLink: String) : this() {
        this.SongName = SongName
        this.SongLink = SongLink
    }

    fun setSongName (Name: String){
        this.SongName = Name
    }
    fun setSongLink (Link: String){
        this.SongLink = Link
    }
    fun getSongName(): String {
        return SongName
    }
    fun getSongLink(): String {
        return SongLink
    }
}