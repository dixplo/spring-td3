package s4.spring.td3.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.td3.models.Group;
import s4.spring.td3.repositories.GroupeRepository;

@RestController
@RequestMapping("/rest/groups")
public class RestGroupesController {
	@Autowired
	private GroupeRepository repo;
	
	@GetMapping("")
	public @ResponseBody List<Group> read(){
		return repo.findAll();
	}
}
