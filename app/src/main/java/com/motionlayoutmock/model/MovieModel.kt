package com.motionlayoutmock.model

data class MovieModel(
        val title: String,
        val description: String,
)

val movieDummyData2: MutableList<MovieModel> = MutableList(6) {
        MovieModel(
                "Brooklyn 99",
                "Comedy series following the exploits of Det. Jake Peralta and his diverse, lovable colleagues as they police the NYPD's 99th Precinct.",
        )
}

val movieDummyData: ArrayList<MovieModel> = arrayListOf(
        MovieModel(
                "Brooklyn 99",
                "Comedy series following the exploits of Det. Jake Peralta and his diverse, lovable colleagues as they police the NYPD's 99th Precinct.",
        ),
        MovieModel(
                "Brooklyn 99",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pellentesque eu tincidunt tortor aliquam nulla.",
        ),
        MovieModel(
                "Brooklyn 99",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pellentesque eu tincidunt tortor aliquam nulla.",
        ),
        MovieModel(
                "Brooklyn 99",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pellentesque eu tincidunt tortor aliquam nulla.",
        ),
        MovieModel(
                "Brooklyn 99",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pellentesque eu tincidunt tortor aliquam nulla.",
        ),
        MovieModel(
                "Brooklyn 99",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pellentesque eu tincidunt tortor aliquam nulla.",
        ),
        MovieModel(
                "Brooklyn 99",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pellentesque eu tincidunt tortor aliquam nulla.",
        ),
        MovieModel(
                "Brooklyn 99",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pellentesque eu tincidunt tortor aliquam nulla.",
        ),

)