package s4.spring.td3.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.td3.models.Organization;
import s4.spring.td3.repositories.OrgaRepository;

@RestController
@RequestMapping("/rest/orgas")
public class RestOrgasController {
	
	@Autowired
	private OrgaRepository orgaRepository;
	
	@GetMapping("")
	public @ResponseBody List<Organization> read(){
		return orgaRepository.findAll();
	}
	
	@GetMapping("{id}")
	public @ResponseBody Organization read(@PathVariable int id){
		Optional<Organization> opt=orgaRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	@PostMapping("create")
	public @ResponseBody Organization create(@RequestBody Organization orga) {
		orgaRepository.save(orga);
		return orga;
	}
	
	@PutMapping("update")
	public @ResponseBody Organization update(@RequestBody Organization orga) {
		orgaRepository.save(orga);
		return orga;
	}
	
	@DeleteMapping("")
	public @ResponseBody Organization delete(@RequestBody Organization orga) {
		orgaRepository.delete(orga);
		return orga;
	}

}
