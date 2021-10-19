package com.example.kotlinwork2_1

class Recording {
    companion object {

        fun getDataBase(): ArrayList<Post> {
            val list = ArrayList<Post>()


           list.add(Post(1,"Дан",1634582976283,"https://www.youtube.com/watch?v=4haurn3S8z8",
               like = false,
               comment = false,
               share = false,0,0,0,"Дементьева 12",
               coordinates = Pair(55.84058,38.20251),type = Types.YoutubeVideo,hidePost = false))



            list.add(Post(2,"Ил",1634583544357,"https://www.youtube.com/watch?v=vd205Q_EE18",
                like = false,
                comment = false,
                share = false,
                0,0,0,
                "Дементьева 12",coordinates = Pair(55.84058,38.20251),
            type = Types.YoutubeVideo,hidePost = false))


            list.add(Post(3,"Хайам",1634650874450,"https://sun1-30.userapi.com/impg/1MiP49SbCxyk8KHQ4svLR10yGIHbUO2ZDdaVCg/Ug4DJFiZ1r0.jpg?size=1280x853&quality=96&sign=a48427054c53e50b6f7deb128d8ccc40&type=album",
                like = false,
                comment = false,
                share = false,
                0,0,0,
                "Дементьева 12",coordinates = Pair(55.84058,38.20251),
                type = Types.SponsoredPosts,hidePost = false,url = "https://vk.com/marvel_dc"))
            return list;
        }

    }
}