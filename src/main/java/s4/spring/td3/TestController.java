package s4.spring.td3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.td3.models.Organization;
import s4.spring.td3.repositories.OrgaRepository;

@Controller
public class TestController {

	@Autowired
	private VueJS vue;
	
	@Autowired
	private OrgaRepository repo;
	
	@RequestMapping("/test/{message}")
	public String index(@PathVariable String message,ModelMap model) {
		vue.addData("message", "Hello world!");
		model.put("vue", vue);
		vue.addMethod("increment", "this.message=parseInt(this.message)+10;");
		return "index";
	}
	@RequestMapping("/spa")
	public String indexSpa(ModelMap model) {
		List<Organization> organizations=repo.findAll();
		vue.addData("items",organizations);
		vue.addData("dialog",false);
		vue.addMethod("loadGroupes", "let self=this;"+Http.get("/rest/groups", "self.items=response.data"));
		model.put("vue", vue);
		return "spa";
	}
}
