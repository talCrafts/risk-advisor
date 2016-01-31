'use strict';
/**
 * Anonymous function, to use as a wrapper
 */

(function() {
  // GLOBAL VARIABLES
  var loaded=false;
  var taClient = null;
  var lastProfile = 'basic';
  var MIN_BAR_SLIDE_PERIOD = 500;

  /**
   * Smooth scroll to any DOM element
   * @param  {String} DOM element
   */
  function jumpTo(h,animate) {
	if (animate === undefined || animate) {
		$('html, body').animate({
			scrollTop: $(h).offset().top
		}, 500);
    }
    else {
    	$(h)[0].scrollIntoView();
    }
  }

  /**
   * Wrapper around the API
   */
  function loadTradeoffAnalytics(profile, themeName, callback, errCallback) {
    taClient = new TA.TradeoffAnalytics({
      dilemmaServiceUrl: '/problem/dilemmas',
      analyticsEventsUrl: '/problem/events',
      customCssUrl: 'https://ta-cdn.mybluemix.net/v1/modmt/styles/' + themeName + '.css',
      profile: profile
    }, 'taWidgetContainer');

    taClient.subscribe('afterError', errCallback);
    
    var topics = [ 'started', 'problemChanged', 'destroyed', 'doneClicked', 'optionClicked', 'X_finalDecisionChanged',
        'X_favoritesChanged', 'X_selectionChanged', 'X_filterChanged'/*, 'X_optionHovered'*/ ];
    topics.forEach(function(t){
      taClient.subscribe(t, function (e){
        console.log(JSON.stringify(e));
      });
    });
    taClient.subscribe('X_selectionChanged', function(selectionChangedEvent){
    	alert(addedOptionKeys);
    	alert(removedOptionKeys);
    	alert(allOptionKeys);
      });
    taClient.start(callback);
  }

  function showTradeoffAnalytcsWidget(problem) {
    taClient.show(problem, onResultsReady, {
    	"dataset-name" : problem.subject
    });
  }

  function destroyTradeoffAnalytcsWidget(callback) {
    taClient.destroy(callback);
  }

  /**
   * Resizes the widget based on the parent DOM element size
   */
  function resizeToParent() {
    taClient.resize();
  }

  function onPageReady() {
	  if(!loaded){
	  	$('.analyze').click();
  	}
  }

  function onPageLoad() {
    loadTradeoffAnalytics('basic', 'watson', onPageReady, onError);
  }

  function onResultsReady() {
    resizeToParent();
    onPageReady();
    jumpTo('#taWidgetContainer');
  }
  
  function onMaximize() {
		$('#minimizeBar').show();
	    $('#taWidgetContainer').addClass('fullsize');
	    $(document.documentElement).addClass('noScroll');

	    window.onkeyup = function(key) {
	      if (key.keyCode === 27) onRestore();
	    };
	    resizeToParent();
  }
  
  function onResultSelection(event) {
	    onRestore();
	    if (event.selectedOptionKeys) {
	      $('.decisionArea').show();
	      var selectedOptionKey = event.selectedOptionKeys[0];//currently, maximum one option is selected 
	      var firstOptionName = currentProblem.options.filter(function(op){
	        return op.key === selectedOptionKey;
	      })[0].name;
	      $('.decisionText').text(firstOptionName);
	      jumpTo('.decisionArea');
	    } else {
	      $('.decisionText').text('');
	      $('.decisionArea').hide();
	    }
  }

  function onError(error) {
	  alert("Error occurred while processing your request:-"+error.errorMessage)
  }
  window.onerror = onError;
  $(document).ready(onPageLoad);
  $('.analyze').on("click",function onAnalyzeClick(){
	    $.getJSON('/problem', function(data) {
	    	showTradeoffAnalytcsWidget(data);
	      });
  });
})();
