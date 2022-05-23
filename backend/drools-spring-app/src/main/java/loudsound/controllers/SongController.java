package loudsound.controllers;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.model.Song;
import loudsound.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping()
    public ResponseEntity<Song> releaseSong(@RequestBody NewSongDTO newSong) {
        return ResponseEntity.ok(songService.releaseSong(newSong));
    }

    @GetMapping()
    public ResponseEntity<Collection<Song>> releaseSong() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @PutMapping("/{songId}/like/{username}")
    public ResponseEntity<Song> likeSong(@PathVariable String songId, @PathVariable String username) {
        return ResponseEntity.ok(songService.likeSong(songId, username));
    }

    @PutMapping("/{songId}/start/{username}")
    public ResponseEntity<Void> startSong(@PathVariable String songId, @PathVariable String username) {
        songService.startSong(songId, username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{songId}/end/{username}")
    public ResponseEntity<Song> endSong(@PathVariable String songId, @PathVariable String username) {
        return ResponseEntity.ok(songService.endSong(songId, username));
    }

}
