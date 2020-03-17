package com.company;

import com.company.model.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int loop = 10;
        DataSource datasource = new DataSource();

        // If data source is not open then return error message
        if (!datasource.open()) {
            System.out.println("Can't open datasource!");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (loop != 0) {
            System.out.println("1. Query songs \n" +
                    "2. Query artists \n" +
                    "3. Query albums \n" +
                    "4. Query artists from a song \n" +
                    "5. Query albums from artists \n" +
                    "6. Insert new entry \n" +
                    "0 to exit");
            System.out.println("Enter the command you'd like to execute: ");
            loop = scanner.nextInt();
            // Method call to read in the newline character
            scanner.nextLine();

            if (loop == 1) {
                //Querying songs
                List<Song> songs = datasource.querySongs(DataSource.ORDER_BY_NONE);
                if (songs == null) {
                    System.out.println("No songs!");
                    return;
                }
                // Displaying the queried songs
                for (Song song : songs) {
                    System.out.println("ID = " + song.getId() + ", Name = " + song.getName() +
                            ", Track = " + song.getTrack() + ", Album = " + song.getAlbumId());
                }
            } else if (loop == 2) {
                //Querying artists
                List<Artist> artists = datasource.queryArtists(DataSource.ORDER_BY_NONE);
                if (artists == null) {
                    System.out.println("No artists!");
                    return;
                }
                // Displaying the queried artists
                for (Artist artist : artists) {
                    System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
                }
            } else if (loop == 3) {
                //Querying albums
                List<Album> albums = datasource.queryAlbums(DataSource.ORDER_BY_NONE);
                if (albums == null) {
                    System.out.println("No albums!");
                    return;
                }
                // Displaying the queried artists
                for (Album album : albums) {
                    System.out.println("ID = " + album.getId() + ", Name = " + album.getName() +
                            ", Artist = " + album.getArtistId());
                }
            } else if (loop == 4) {
                String title;
                // User input for title of song to find related artists for
                System.out.println("Enter song title to find related artists: ");
                title = scanner.nextLine();

                // Querying artists based on song
                List<SongArtist> songArtists = datasource.queryArtistsForSong(title, DataSource.ORDER_BY_ASC);
                if (songArtists == null) {
                    System.out.println("Couldn't find the artist for the song");
                    return;
                }

                // Displaying queried artists
                for (SongArtist artist : songArtists) {
                    System.out.println("Artist name = " + artist.getArtistName() +
                            "\nAlbum name = " + artist.getAlbumName() +
                            "\nTrack = " + artist.getTrack());
                }

            } else if (loop == 5) {
                String artist;
                // User input for artist to find related albums
                System.out.println("Enter artist to find related albums: ");
                artist = scanner.nextLine();

                // Querying albums from artists
                List<String> albumsForArtist = datasource.queryAlbumsForArtist(artist, DataSource.ORDER_BY_ASC);

                if(!albumsForArtist.isEmpty()) {
                    // Displaying the queried albums if there are any
                    for (String album : albumsForArtist) {
                        System.out.println(album);
                    }
                } else {
                    System.out.println("No albums related to that artist!");
                }

            } else if (loop == 6) {
                String title, artist, album;
                int track;

                System.out.println("Enter title of song: ");
                title = scanner.nextLine();

                System.out.println("Enter artist name: ");
                artist = scanner.nextLine();

                System.out.println("Enter title of album: ");
                album = scanner.nextLine();

                System.out.println("Enter track number: ");
                track = scanner.nextInt();
                scanner.nextLine();

                datasource.insertSong(title, artist, album, track);
//                System.out.println(title + ", " + artist + ", " + album + ", " + track);
            } else if (loop == 0) {
                System.out.println("Thank you. Goodbye!");
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }

        datasource.close();
    }
}
