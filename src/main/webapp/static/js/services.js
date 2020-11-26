﻿(function() {
'use strict';

/*
	angular.module('app.services')
	.service('Rate',['ideasFactory'  ,Rate]);
	function Rate(ideasFactory) {
		   return {
			changeRate:function (mark, idea) {
			  idea.rating= idea.rating + (+mark);
			  ideasFactory.updateIdea(idea);
			  return idea;
			  }
		  };
	};
*/

angular.module('app.services')
.service('detailsService', detailsService);
function detailsService() {

       return{
            getCategories:function(){
                return [
                    {
                        id:0,
                        descr:'Sport'
                    },
                    {
                        id:1,
                        descr:'Transport'
                    },
                    {
                        id:2,
                        descr:'Культура'
                    }
                    ];
            }//getCategories
       };
};
})();