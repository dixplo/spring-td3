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
		vue.addData("snackbar",false);
		vue.addData("message");
		vue.addDataRaw("orga","{name:'Le Nom',domain:''}");
		vue.addMethod("loadGroupes", "let self=this;"+Http.get("/rest/groups", "self.items=response.data"));
		
		vue.addMethod("addOrga", "let self=this;"+Http.post("/rest/orgas/create", "this.orga", "self.dialog=false;"
				+ "self.message='Organisation ajoutée';"
				+ "self.snackbar=true;"
				+ "self.items.push(response.data);self.orga={};"));

		vue.addMethod("updateOrga", "let self=this;self.dialog=true;self.orga=orga","orga");
		
		vue.addMethod("deleteOrga", 
				"let self=this;let $='';"+Http.delete("'/rest/orgas/'+item.id+$","self.message=response.data;"
						+ "self.snackbar=true;"
						+ "self.items.splice(index,1);"),"item","index");
		model.put("vue", vue);
		return "spa";
	}
}







