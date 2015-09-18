var ConversionUtility = require('../promiseConversion-utility.js');

var DetailsPage = function() {comments = new CommentList()};

DetailsPage.prototype = Object.create({}, {
	updateButton: { get: function() { return element(by.id('btnUpdate')); }},

	title:   { get: function() { return element.all(by.id('tdData')).get(0); }},
	desc:    { get: function() { return element.all(by.id('tdData')).get(1); }},
	author:  { get: function() { return element.all(by.id('tdData')).get(2); }},
	rating:  { get: function() { return element(by.id('rating')); }},

	commentField:   { get: function() { return element(by.id('newComment')); }},
	addCommentButton:  { get: function() { return element(by.id('btnAddComment')); }},

	comments: { get: function() { return comments; }},

	getTitle:   { value: function() { return this.title.getText(); }},
	getDesc:    { value: function() { return this.desc.getText(); }},
	getAuthor:  { value: function() { return this.author.getText(); }},
	getRating:  { value: function() { return ConversionUtility.getIntValue(this.rating); }},

	likeButton:    { get: function() { return element(by.css('.btn-thumbs')); }},
	like:          { value: function() { return this.likeButton.click(); }},

	addComment:    { value: function(body) {
		this.commentField.sendKeys(body);
		this.addCommentButton.click();
	}},

	update: { value: function() { this.updateButton.click(); }}
});

var CommentList = function() {};

CommentList.prototype = Object.create({}, {

	commentAuthors:        { get: function() { return element.all(by.css('.commentAuthor')); }},
	commentBodies:         { get: function() { return element.all(by.css('.commentBody')); }},
	commentRatings:        { get: function() { return element.all(by.id('divCommentRating')); }},

	getAuthor:  { value: function(id) { return this.commentAuthors.get(id).getText(); }},
	getBody:    { value: function(id) { return this.commentBodies.get(id).getText(); }},
	getRating:  { value: function(id) { return ConversionUtility.getIntValue(this.commentRatings.get(id).getText()); }},

	likeButtons:   { get: function() { return element.all(by.css('.likeButton')); }},
	like:          { value: function(id) { return this.likeButtons.get(0).click(); }}
});

module.exports = DetailsPage;