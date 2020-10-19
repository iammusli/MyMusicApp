package iammusli.mymusicapp

class Artists() {
    private var ArtistID: Int = 0
    private var ArtistName: String = "Nesto ne valja"
    private var ArtistImage: String = "Nesto ne valja"

    constructor(ArtistID: Int, ArtistName: String, ArtistImage: String) : this() {
        this.ArtistID = ArtistID
        this.ArtistName = ArtistName
        this.ArtistImage = ArtistImage
    }

    fun getID() : Int {
        return ArtistID
    }
    fun getName(): String {
        return ArtistName
    }
    fun getImage(): String {
        return ArtistImage
    }
    fun setID(ID: Int) {
        this.ArtistID = ID
    }
    fun setName(NAME: String) {
        this.ArtistName = NAME
    }
    fun setImage(IMAGE: String) {
        this.ArtistImage = IMAGE
    }
}