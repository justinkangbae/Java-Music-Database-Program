# Java-Music-Database-Program

In the src folder, you will see all of source code to see how the program works. 
The DataSource file contains most of the logic for accessing the database and such.

## DataSource file

"open" function - begins the connection the the database and creates and prepares a bunch of PreparedStatement objects.  
"close" function - closes all possible connections with database or still open PreparedStatement objects.  
"querySongs" function - queries the database for all the songs in the database.              
"queryArtists" function - queries the database for all the artists in the database.  
"queryAlbums" function - queries the database for all the albums in the database.  
"queryAlbumsForArtist" function - accepts user input for artists and returns albums from that artist by using joins on tables.  
"queryArtistsForSong" function - accepts user input for a song and returns related artists by using joins on tables.  
"querySongsMetadata" function - gets metadata of songs.  
"insertArtist" function - inserts artist into database.  
"insertAlbum" function - inserts album into database (this function depends on previous insert function).  
"insertSong" function - inserts song into database (this function depends on both previous insert functions working).  
