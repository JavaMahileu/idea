﻿//------------------------------------------------------------------------------------------------
// addNewIdea
//------------------------------------------------------------------------------------------------
describe('Add Idea controllers testing', function(){

	var state;

	//controllers;
	var addNewIdeaTest;

	//scopes
	var addNewIdeaScope;

	beforeEach(angular.mock.module('ui.router'));
	beforeEach(angular.mock.module('app.controllers'));
	beforeEach(angular.mock.module('app.services'));
	beforeEach(angular.mock.module('ideaFactories'));
	//beforeEach(angular.mock.module('ideaApp'));


	beforeEach(inject(function($state, $rootScope, $controller, $window) {
		addNewIdeaScope = $rootScope.$new();

		state = $state;
		window = $window;

		//details
		var vIdeaDetails = {
				description: 'Some text'
			};

		//init serviceInvoke
		serviceInvoke = function(aId){
			return{
				then: function(func1){
					func1.apply(vIdeaDetails);
				}
			}
		};

		myServiceInvoke = {
			getIdeaById: serviceInvoke
		};

		//details service invoke
		dServiceInvoke = {
			getCategories: function(){
				return ['op1', 'op2'];
			}
		};


		addNewIdeaTest = function(){
				return $controller('addNewIdea', {
					$scope:addNewIdeaScope,
					$window:window,
					$modal:function(){},
					detailsService:dServiceInvoke,
					ideasFactory: myServiceInvoke,
					mapGeoService: function(){},
					imgur: function(){},
					dialogs: function(){}/*,
					loadModalWindow: function(){}*/
				});
		};
	}));

	// Verify that the factory can be instantiated
	it('add new idea controller should be instantiable', function () {
		expect(addNewIdeaTest).toBeDefined();
	});

	//look at scope variable in controller
	it('add new idea controller check scope', function () {
		// Make our assertions
		expect(addNewIdeaScope).toBeDefined();
		//addNewIdeaTest
		var ctrl = addNewIdeaTest();
		expect(ctrl).toBeDefined();

		expect(addNewIdeaScope.bottomButtonName).toBeDefined();
		expect(addNewIdeaScope.bottomButtonName).toEqual('Добавить');
		expect(addNewIdeaScope.data).toBeNull();
	});

});


//------------------------------------------------------------------------------------------------
// updateIdeaTest
//------------------------------------------------------------------------------------------------
describe('Update Idea controllers testing', function(){

	var state;

	//controllers;
	var updateIdeaTest;

	//scopes
	var updateIdeaScope;

	beforeEach(angular.mock.module('ui.router'));
	beforeEach(angular.mock.module('app.controllers'));
	beforeEach(angular.mock.module('app.services'));
	beforeEach(angular.mock.module('ideaFactories'));
	//beforeEach(angular.mock.module('ideaApp'));


	beforeEach(inject(function($state, $rootScope, $controller) {
		updateIdeaScope = $rootScope.$new();

		state = $state;

		//details
		var vIdeaDetails = {
				description: 'Some text'
			};

		//updateIdea
		updateIdeaTest = function(){
				return $controller('updateIdea', {
					$scope:updateIdeaScope,
					$state:state,
					ideaDetails: vIdeaDetails
				});
		};
	}));

	// Verify that the factory can be instantiated
	it('update idea details controller should be instantiable', function () {
		expect(updateIdeaTest).toBeDefined();
	});

	//look at scope variable in controller
	it('update new idea controller check scope', function () {
		expect(updateIdeaScope).toBeDefined();

		var ctrl = updateIdeaTest();
		expect(ctrl).toBeDefined();

		// Make our assertions
		expect(updateIdeaScope.bottomButtonName).toBeDefined();
		expect(updateIdeaScope.bottomButtonName).toBe('Обновить');
		expect(updateIdeaScope.data).toBeDefined();
	});
});

//------------------------------------------------------------------------------------------------
// detailsIdeaTest
//------------------------------------------------------------------------------------------------
describe('Idea details controllers testing', function(){

	var state;

	//controllers;
	var detailsIdeaTest;

	//service mock
	var serviceInvoke;
	var myServiceInvoke;

	//scopes
	var detailsIdeaScope;

	beforeEach(angular.mock.module('ui.router'));
	beforeEach(angular.mock.module('app.controllers'));
	beforeEach(angular.mock.module('app.services'));
	//beforeEach(angular.mock.module('ideasFactory'));
	//beforeEach(angular.mock.module('ideaApp'));


	beforeEach(inject(function($state, $rootScope, $controller) {
		detailsIdeaScope = $rootScope.$new();

		state = $state;

		var vIdeaDetails = {
				id:1,
				description: 'Some text'
			};

		//init serviceInvoke
		serviceInvoke = function(aId){
			return{
				then: function(func1){
					func1.apply(vIdeaDetails);
				}
			}
		};

		myServiceInvoke = {
			getIdeaById: serviceInvoke
		};

		//detailsCtrl
		detailsIdeaTest  = function(){
				return $controller('detailsCtrl', {
					$scope:detailsIdeaScope,
					$state:state,
					ideasFactory: myServiceInvoke,
					ideaDetails: vIdeaDetails
				});
		};
	}));

	// Verify that the factory can be instantiated
	it('idea details controller should be instantiable', function () {
		expect(detailsIdeaTest).toBeDefined();
	});

	//look at scope variable in controller
	it('idea details controller check scope', function () {
		expect(detailsIdeaScope).toBeDefined();

		var ctrl = detailsIdeaTest();
		expect(ctrl).toBeDefined();
		// Make our assertions
		//expect(data).toBeDefined();
		expect(detailsIdeaScope.data).toBeUndefined();
	});

});


//------------------------------------------------------------------------------------------------
// ideasCtrlTest
//------------------------------------------------------------------------------------------------

describe('Idea controllers testing', function(){

	var state;

	//controllers;
	var ideasCtrlTest;

	//scopes
	var ideasCtrlIdeaScope;

	beforeEach(angular.mock.module('ui.router'));
	beforeEach(angular.mock.module('app.controllers'));
	beforeEach(angular.mock.module('app.services'));
	beforeEach(angular.mock.module('ideaFactories'));
	//beforeEach(angular.mock.module('ideaApp'));


	beforeEach(inject(function($state, $rootScope, $controller) {
		ideasCtrlIdeaScope = $rootScope.$new();

		state = $state;

		//ideasCtrl
		ideasCtrlTest  = function(){
				return $controller('ideasCtrl', {
					$scope:ideasCtrlIdeaScope
				});
		};
	}));

	// Verify that the factory can be instantiated
	it('idea details controller should be instantiable', function () {
		expect(ideasCtrlTest).toBeDefined();
	});

	//look at scope variable in controller
	it('idea controller check scope', function () {

		var ctrl = ideasCtrlTest();
		expect(ctrl).toBeDefined();
	});


});
