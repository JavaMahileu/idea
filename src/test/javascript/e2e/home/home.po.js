var ConversionUtility= require('../promiseConversion-utility.js');

var HomePage = function() {};
HomePage.prototype = Object.create({}, {
	popularIdeas:   { get: function() { return element.all(by.repeater('idea in ideasCtrl.popular')); }},
	latestIdeas:    { get: function() { return element.all(by.repeater('idea in ideasCtrl.latest')); }},

	loginButton:    { get: function() { return element(by.id('words')); }},
	addButton:      { get: function() { return element(by.id('addIdeaButton')); }},
	allButton:      { get: function() { return element(by.id('all')); }},
	tagButtons:     { get: function() { return element.all(by.repeater('tag in tagsCtrl.tagsTop')); }},
	likeButtons:    { get: function() { return element.all(by.css('.btn-thumbs')); }},

	ratings:        { get: function() { return element.all(by.id('rating')); }},
	ideaLogos:      { get: function() { return element.all(by.id('imgIdeaLogo')); }},

	login:      { value: function() { return this.loginButton.click(); }},
	addIdea:    { value: function() { return this.addButton.click(); }},
	getAll:     { value: function() { return this.allButton.click(); }},
	getByTag:   { value: function(id) { return this.tagButtons.get(id).click(); }},
	getRating:  { value: function(id) { return ConversionUtility.getIntValue(this.ratings.get(id)); }},
	like:       { value: function(id) { return this.likeButtons.get(id).click(); }},
	ideaDetails:{ value: function(id) { return this.ideaLogos.get(id).click(); }},

	getPage:    { value: function() { browser.get('http://evbymogsd0030.minsk.epam.com:7080/idea'); }}
});

module.exports = HomePage;
