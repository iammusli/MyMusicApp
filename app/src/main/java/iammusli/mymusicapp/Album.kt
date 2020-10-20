package iammusli.mymusicapp

class Album(){
        private var AlbumYear: String = "Nesto ne valja"
        private var AlbumName: String = "Nesto ne valja"
        private var AlbumImage: String = "Nesto ne valja"

        constructor(AlbumYear: String, AlbumName: String, AlbumImage: String) : this() {
            this.AlbumYear = AlbumYear
            this.AlbumName = AlbumName
            this.AlbumImage = AlbumImage
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
