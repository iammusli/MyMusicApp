package iammusli.mymusicapp

class Album(){
        private var AlbumID: Int = 0
        private var AlbumYear: String = "Nesto ne valja"
        private var AlbumName: String = "Nesto ne valja"
        private var AlbumImage: String = "Nesto ne valja"

        constructor(AlbumID: Int, AlbumYear: String, AlbumName: String, AlbumImage: String) : this() {
            this.AlbumID = AlbumID
            this.AlbumYear = AlbumYear
            this.AlbumName = AlbumName
            this.AlbumImage = AlbumImage
        }

        fun getID(): Int {
            return AlbumID
        }
        fun getYear(): String {
            return AlbumYear
        }
        fun getName(): String {
            return AlbumName
        }
        fun getImage(): String {
            return AlbumImage
        }
        fun setID(ID: Int) {
            this.AlbumID = ID
        }
        fun setYear(YEAR: String) {
           this.AlbumYear = YEAR
         }
        fun setName(NAME: String) {
            this.AlbumName = NAME
        }
        fun setImage(IMAGE: String) {
            this.AlbumImage = IMAGE
        }
    }
