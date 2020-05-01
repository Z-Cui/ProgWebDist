package com.zc.bookmarkdb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zc.bookmarkdb.crudrepository.BookmarkRepository;
import com.zc.bookmarkdb.crudrepository.HistoryRepository;
import com.zc.bookmarkdb.crudrepository.TagRepository;
import com.zc.bookmarkdb.object.Bookmark;
import com.zc.bookmarkdb.object.History;
import com.zc.bookmarkdb.object.Tag;

@RestController
@RequestMapping(path="/")
@CrossOrigin	
public class BookmarkController {
	
	@Autowired
	private BookmarkRepository bookmarkRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private TagRepository tagRepository;

	
	private static final Logger log = LoggerFactory.getLogger(BookmarkController.class);
	
	@GetMapping(path="/")
	public @ResponseBody String nothingToDo() {
		return "You received a message from Bookmark Database Microservice.";
	}
	
	// return all bookmarks
	@GetMapping(path="/all")
	public @ResponseBody List<Bookmark> getBookmarks() {
		log.info("Request: all bookmarks");
		return bookmarkRepository.findAll();
	}
	
	// return all bookmarks of a user
	@GetMapping(path="/user/{savedUser}")
	public @ResponseBody List<Bookmark> getUsersBookmarks(@PathVariable(value = "savedUser") String savedUser) {
		log.info("Request: bookmarks of a user: " + savedUser);
		return (List<Bookmark>)bookmarkRepository.findBySavedUser(savedUser);
	}
	
	// return all tags
	@GetMapping(path="/tags")
	public @ResponseBody List<Tag> getTags() {
		log.info("Request: all tags");
		return (List<Tag>)tagRepository.findAll();
	}
	
	// add a bookmark
	@PostMapping(path="/add")
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addBookmark(
			@RequestBody Bookmark b) {
		
		LocalDateTime t = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String description = t.format(formatter) + " : created bookmark [" + b.getName() + "], [" + b.getUrl() + "]";
		b.addHistory(new History(description));
		
		bookmarkRepository.save(b);
		log.info(description + " [" + b.getName() + "], [" + b.getUrl() +"]");
	}
	
	// delete a bookmark
	@PutMapping(path="/delete/{id}")
	public void deleteBookmark(@PathVariable(value = "id") long id) {
		
		Bookmark b = bookmarkRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark ID:"+id));
		
		String name = b.getName();
		String url = b.getUrl();
		
		bookmarkRepository.delete(b);
		
		LocalDateTime t = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		log.info(t.format(formatter) + ": deleted bookmark [" + name + "], [" + url + "]");
	}
	
	// add click count of a bookmark
	@PutMapping(path="/click/{id}")
	public void addClickCount(@PathVariable(value = "id") long id) {
		
		Bookmark b = bookmarkRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark ID:"+id));
		
		b.addClick();
		
		LocalDateTime t = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String description = t.format(formatter) + " : clicked on bookmark";
		b.addHistory(new History(description));
		
		bookmarkRepository.save(b);
		log.info(description + " [" + b.getName() + "], [" + b.getUrl() +"]");
	}
	
	// change bookmark name
	@PutMapping("/edit-name/{id}")
	public void changeBookmarkName(
			@PathVariable(value = "id") long id,
			@RequestParam String newName) {
		
		Bookmark b = bookmarkRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark ID:"+id));
		String oldName = b.getName();
		b.setName(newName);
		
		LocalDateTime t = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String description = t.format(formatter) + " : edited name from [" + oldName + "] -> [" + newName + "]";
		b.addHistory(new History(description));
		
		bookmarkRepository.save(b);
		log.info(description + " for bookmark [" + b.getName() + "], [" + b.getUrl() +"]");
	}
	
	// change bookmark url
	@PutMapping("/edit-url/{id}")
	public void changeBookmarkUrl(
			@PathVariable(value = "id") long id,
			@RequestParam String newUrl) {
		
		Bookmark b = bookmarkRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark ID:"+id));
		String oldUrl = b.getUrl();
		b.setUrl(newUrl);
		
		LocalDateTime t = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String description = t.format(formatter) + " : edited url from [" + oldUrl + "] -> [" + newUrl + "]";
		b.addHistory(new History(description));
		
		bookmarkRepository.save(b);
		log.info(description + " for bookmark [" + b.getName() + "], [" + b.getUrl() +"]");
	}
	
	// add tag to a bookmark
	@PutMapping(path="/add-tag/{id}")
	public void addTag(
			@PathVariable(value = "id") long id,
			@RequestParam String tagName) {
		
		Bookmark b = bookmarkRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark ID:" + id));
		
		// find the tag with the same name in tagRepository, if it doesn't exist create one
		Tag tg = tagRepository.findByName(tagName).orElse(new Tag(tagName));
		
		b.getTags().add(tg);
		
		LocalDateTime t = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String description = t.format(formatter) + " : added tag [" + tagName + "] to bookmark [" + b.getName() + "], [" + b.getUrl() + "]";
		b.addHistory(new History(description));
		
		bookmarkRepository.save(b);
		log.info(description);
	}
	
	// remove tag to a bookmark
	@PutMapping(path="/remove-tag/{id}")
	public void removeTag(
			@PathVariable(value = "id") long id,
			@RequestParam String tagName) {
		
		Bookmark b = bookmarkRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark ID:" + id));
		
		Tag tg = tagRepository.findByName(tagName)
				.orElseThrow(() -> new IllegalArgumentException("Invalid tag name: [" + tagName + "] doesn't exist"));
		
		b.getTags().remove(tg);
		
		LocalDateTime t = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String description = t.format(formatter) + " : removed tag [" + tagName + "] from bookmark [" + b.getName() + "], [" + b.getUrl() + "]";
		b.addHistory(new History(description));
		
		bookmarkRepository.save(b);
		log.info(description);
	}
	
	// return one single bookmark
	@GetMapping("/one/{id}")
	public @ResponseBody Bookmark returnOneBookmark(
			@PathVariable(value = "id") long id) {
		
		Bookmark b = bookmarkRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark ID:"+id));
		
		log.info("Request: one bookmark");
		return b;
	}
	
	// return all histories of a bookmark
	@GetMapping(path="/history/{id}")
	public @ResponseBody Set<History> getHistory(@PathVariable(value = "id") long id) {
		
		Bookmark b = bookmarkRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark ID:"+id));
		
		log.info("Request: all histories of a bookmark");
		return b.getHistory();
	}
	
	// return all tags of a bookmark
	@GetMapping(path="/tag/{id}")
	public @ResponseBody Set<Tag> getTags(@PathVariable(value = "id") long id) {
		
		Bookmark b = bookmarkRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark ID:"+id));
		
		log.info("Request: all tags of a bookmark");
		return b.getTags();
	}
	
	
}

