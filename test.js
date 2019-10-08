Vue.prototype.$http = axios;
new Vue({
	"el" : "v-app",
	"vuetify" : new Vuetify(),
	"delimiters" : [ "<%", "%>" ],
	"data" : function() {
		return {
			"dialog" : false,
			"orga" : {
				name : 'Le Nom',
				domain : ''
			},
			"snackbar" : false,
			"message" : "",
			"items" : [ {
				"id" : 1,
				"name" : "Foo",
				"domain" : "foo.com",
				"aliases" : "foo",
				"groups" : [ {
					"id" : 1,
					"name" : "Bar",
					"email" : "bar@email.fr",
					"aliases" : "bars",
					"users" : []
				} ],
				"users" : []
			}, {
				"id" : 2,
				"name" : "plop200",
				"domain" : null,
				"aliases" : null,
				"groups" : [],
				"users" : []
			}, {
				"id" : 6,
				"name" : "Le Nom",
				"domain" : "",
				"aliases" : null,
				"groups" : [],
				"users" : []
			} ]
		};
	},
	"methods" : {
		"deleteOrga" : function(item, index) {
			let self = this;
			let $ = '';
			this.$http['delete']('/rest/orgas/' + item.id + $, {}).then(
					function(response) {
						self.message = response.data;
						self.snackbar = true;
						self.items.splice(index, 1);
					});
		},
		"addOrga" : function() {
			let self = this;
			this.$http['post']('/rest/orgas/create', this.orga).then(
					function(response) {
						self.dialog = false;
						self.message = 'Organisation ajoutée';
						self.snackbar = true;
						self.items.push(response.data);
						self.orga = {};
					});
		},
		"loadGroupes" : function() {
			let self = this;
			this.$http['get']('/rest/groups', {}).then(function(response) {
				self.items = response.data
			});
		}
	}
});