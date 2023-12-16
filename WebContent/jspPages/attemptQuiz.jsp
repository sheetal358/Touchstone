   <%@taglib uri="http://com.swinfosoft.mvc.web.tags" prefix="mvc"%>
   <mvc:invoke action="attemptQuiz"/>
    <div data-bind="foreach:quiz" class="span6 offset1" >
        <input type="hidden" name="quizId" data-bind="value:id" /> 
        <table class="table table-bordered">
        	<tbody>
        		<tr>
        			<td> <div style="float: left">Quiz Name: <span data-bind="text:name"></span></div></td>
        			<td><div style="float: right">Duration: <span data-bind="text:duration"></span>&nbsp;Timer: <span data-bind="text:time"></span></div></td>
        		</tr>
        	</tbody>
        </table>         
        <div style="clear: both"></div>
        <div data-bind="foreach:questions" style="height:250px; position: relative;">            
            <table style="width:100%; position: absolute; top:0px;left:0px;" class="table table-bordered table-striped" data-bind="visible:visible($parent.currentSequence())">
                <thead>
                	<tr><td colspan="2">
                	<table class="table">
                	 <tr>
                        <th style="border: 0;">Q. No.&nbsp;<span data-bind="text:sequence+1"></span>:&nbsp;<span data-bind="text:question"></span>
                         <input type="hidden" name="questionId" data-bind="value:id" /></th>
                        <th style="text-align: right; width: 150px; border: 0;">Q. Type:&nbsp;<span data-bind="text:typeText"></span><br/>
                        Timer:&nbsp;<span data-bind="text:time"></span>
                        </th>
                    </tr>
                	</table>
                	</td></tr>
                   
                </thead>
                <tbody data-bind="foreach:options">
                    <tr>
                        <td width="40px;">
                            <input type="radio" data-bind="attr: {name:'opt' + $parent.id}, click: $parent.setAnswer, value: sqn" />
                        </td>
                        <td data-bind="text:text"></td>
                    </tr>
                </tbody>

            </table>
        </div>
        <div style="clear: both;"></div>
        <div style="float: left" data-bind="visible:visiblePrevious,click:previousClick"><input type="button" class="btn btn-primary" value="Previous"></input></div>
        <div style="float: right" data-bind="visible:visibleNext,click:nextClick"><input type="button" class="btn btn-primary" value="Next"></input></div>
        <div style="float: right" data-bind="visible:visibleNext,click:skipClick"><input type="button" class="btn btn-primary" value="Skip"></input>&nbsp;&nbsp;</div>
        <div style="float: right" data-bind="visible:visibleSubmit,click:submitClick"><input type="button" class="btn btn-primary" value="Submit"></input></div>
    </div>
    <script>
       
        function Quiz(id, name, duration, questions, totalQuestion,quizTimer) {
            var self = this;
            self.id = id;
            self.name = name;
            self.duration = duration;
            self.questions = ko.observableArray(questions);
            self.totalQuestion = totalQuestion;
            self.currentSequence = ko.observable(0);
            self.timeTaken=ko.observable(0);            
            self.quizTimer=ko.observable(quizTimer);
            self.time=ko.computed(function(){
            	return toHHMMSS(self.timeTaken());
            });
            self.visiblePrevious = ko.computed(function () {
                if (self.currentSequence() == 0)
                    return 0;
                else
                    return 1;
            });
            self.visibleNext = ko.computed(function () {
            	startQuestionTimer(self);
                if (self.currentSequence() == (self.totalQuestion - 1))
                    return 0;
                else
                	return 1;
               
            });
            self.visibleSubmit = ko.computed(function () {
                if (self.currentSequence() == (self.totalQuestion - 1))
                    return 1;
                else
                    return 0;
            });
            self.previousClick = function () {
            	/* var flag=validateSelection(self);
            	if(!flag)
            		return flag; */
            	stopQuestionTimer(self,"prev");
                self.currentSequence(self.currentSequence() - 1);
            };
            self.nextClick = function () {
            	var flag=validateSelection(self);
            	if(!flag)
            		return flag;
            	stopQuestionTimer(self);
                self.currentSequence(self.currentSequence() + 1);
            };
            self.skipClick = function () {
            	stopQuestionTimer(self,"pause");
                self.currentSequence(self.currentSequence() + 1);
            };
            self.submitClick = function () {
            	var flag=validateSelection(self);
            	if(!flag)
            		return flag;
            	 stopQuestionTimer(self);
                makeData(self);
            };
           
            self.quizTimer().StartCounter(self.timeTaken,self.duration);
            self.quizTimer().Callback=function(){
            	 makeData(self);
            };
        }
        
         function startQuestionTimer(quiz)
        {
        	var currQues=quiz.questions()[quiz.currentSequence()];
        	currQues.quizTimer().StartCounter(currQues.timeTaken);        	
        }
        function stopQuestionTimer(quiz,pause)
        {
        	var currQues=quiz.questions()[quiz.currentSequence()];
        	if(pause)
        		{
        		if(pause=="prev")
        			{
        			if(currQues.selectedAns()!=0)
        				currQues.quizTimer().StopCounter();
        			else
        	currQues.quizTimer().StopCounter(pause);
        			}
        		else
        			currQues.quizTimer().StopCounter(pause);
        		}
        	else
        		currQues.quizTimer().StopCounter();
        }  
        function validateSelection(quiz)
        {
        	var flag=false;
            var currQues=quiz.questions()[quiz.currentSequence()];
            if(currQues.selectedAns())
        	if(currQues.selectedAns() !=0)
           flag=true;
        	
        	if(!flag)
        		alert('Please select any answer.');
        	/* $("#alertText").html('Please select any answer.');
    		$("#alert_div").modal("show"); */
    		return flag;
        }
        function Questions(id, question, type, options, sequence,quizTimer,ans,dLevel) {
            var self = this;
            self.id = id;
            self.question = question;
            self.type = type;
            self.options = ko.observableArray(options);
            self.sequence = sequence;
            self.selectedAns=ko.observable(0);
            self.timeTaken=ko.observable(0);            
            self.quizTimer=ko.observable(quizTimer);
            self.time=ko.computed(function(){
            	return toHHMMSS(self.timeTaken());
            });
            self.typeText = ko.computed(function () {
                return (type == 1) ? 'True/False' : 'Multi Choise';
            });
            self.visible = function (seq) {
                if (seq == self.sequence)
                    return 1;
                else
                    return 0;
            };
            self.setAnswer=function(opt){
            	self.selectedAns(opt.sqn);
            	return true;
            };
        }
        function Options(sqn, text) {
            self = this;
            self.sqn = sqn;
            self.text = text;
        }
      

         var quizJson= ${quiz}; 
         var questJson=${questions};
         var quiz=[];
         var varQuestions = [];
         var totalQuestion = 0;
         var isQuesetionAvailable=false;
         if(questJson.length != 0)
         	isQuesetionAvailable=true;
         
         if(isQuesetionAvailable)
        	 {
         $(questJson).each(function (j, valQue) {
             var varOptions = [];
             totalQuestion = j;
             if(valQue.type==1)
            	 {
            	 varOptions.push(new Options(1, "True"));
            	 varOptions.push(new Options(2, "False"));
            	 }
             else
            	 {
             $(valQue.options).each(function (k, valOpt) {
            	 if(valOpt != null)
                 varOptions.push(new Options(k+1, valOpt));
             });
            	 }
             varQuestions.push(new Questions(valQue.id, valQue.description, valQue.type, varOptions, j, new QuestionTimer()));
         });
         quiz.push(new Quiz(quizJson.id, quizJson.name, quizJson.duration, varQuestions, totalQuestion + 1,new QuizTimer()));
        	 }
        //*********************************************************
        ko.bindingHandlers.visible = {
            init: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
                var value = ko.utils.unwrapObservable(valueAccessor());

                var $element = $(element);

                if (value)
                    $element.show();
                else
                    $element.hide();
            },
            update: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
                var value = ko.utils.unwrapObservable(valueAccessor());

                var $element = $(element);


                var allBindings = allBindingsAccessor();

                // Grab data from binding property
                var duration = allBindings.duration || 500;
                var isCurrentlyVisible = !(element.style.display == "none");

                if (value && !isCurrentlyVisible)
                    $element.show(duration);
                else if ((!value) && isCurrentlyVisible) {
                    $element.hide(duration);
                    //$element.css({ "left": function () { return $(this).offset().left; } }).animate({ "left": "-1000px" }, "slow");
                    //$element.hide();
                }
            }
        };
        //*********************************************************
        if(isQuesetionAvailable)
        	 {
         ko.applyBindings(quiz); 
        	 }
        else
        	{
        	showNotification("No question available");
        	}
        //Set Data to submit *************
        function makeData(quizData)
        {
        	
        	var data={};
        	data.id=quizData.id;
        	  data.timeTaken=quizData.timeTaken();
        	data.totalQuestion=quizData.totalQuestion;
        	data.currentSequence=quizData.currentSequence();
        	data.duration=quizData.duration;
        	var i=0;
        	ko.utils.arrayForEach(quizData.questions(), function(question) {
        		i+=1;
               var ques="question"+ i;
               var quesData={};
               quesData.questionId=question.id;
               quesData.answer=question.selectedAns();
               quesData.timeTaken=question.timeTaken();
               data[ques]=ko.toJSON(quesData);
            });
        		//var quizSubmitJson=ko.toJSON(data);
        	//alert(ko.toJSON(data)); 
         	 $.post("saveQuiz.process",data,function(data){
         		$("#notification_div").html(data);
				$("#home_body").html($("#notification_div").clone());
				$("#notification_div").show();
        	});  
        }
        //********************************
        //Timer Function **************************
       function QuizTimer(){
        	var self=this;
        	self.timerId=0;
        	self.elapsedTime=ko.observable(0);
        	self.isAttempted=ko.observable(false);
        	self.StartCounter=function(timeTaken,duration){
        		if(self.isAttempted())
        			return true;
        		self.elapsedTime(duration * 60);
        		self.timerId=window.setInterval(function(){
        			self.elapsedTime(self.elapsedTime()-1);
        			timeTaken(self.elapsedTime());
        			var totalTofinish=duration * 60;
        			if(self.elapsedTime()== 0)
        				{
        				clearInterval(self.timerId);
        				self.Callback();
        				self.isAttempted(true);
        				} 
        		},1000);
        	};
        	
        	self.StopCounter=function(){
        		clearInterval(self.timerId);
        		self.isAttempted(true);
        	};
        	self.Callback=function(){};
        }
       function QuestionTimer(){
       	var self=this;
       	self.timerId=0;
       	self.elapsedTime=ko.observable(0);
       	self.isAttempted=ko.observable(false);
       	self.isRunning=ko.observable(false);
       	self.StartCounter=function(timeTaken){
       		if(self.isAttempted())
       			return true;
       		if(self.isRunning()==false)
       			{
       		self.elapsedTime(0);
       		self.isRunning(true);
       			}
       		self.timerId=window.setInterval(function(){
       			self.elapsedTime(self.elapsedTime()+1);
       			timeTaken(self.elapsedTime());       			 
       		},1000);
       	};
       	
       	self.StopCounter=function(pause){
       		clearInterval(self.timerId);       		
       		if(pause)
       			self.isRunning(true);
       		else
       			{
       			self.isRunning(false);
       			self.isAttempted(true);
       			}
       	};
       	self.Callback=function(){};
       }
      //Timer Function end **************************
      //Second to Time ***********************
      function toHHMMSS(sec){
    	  var sec_num=parseInt(sec,10);
    	  var hours=Math.floor(sec_num/3600);
    	  var minutes=Math.floor((sec_num-(hours*3600))/60);
    	  var seconds=sec_num-(hours*3600)-(minutes*60);
    	  
    	  if(hours<10){hours="0"+hours;}
    	  if(minutes<10){minutes="0"+minutes;}
    	  if(seconds<10){seconds="0"+seconds;}
    	  var time=hours+":"+minutes+":"+seconds;
    	  return time;
      }
      //**************************************
    </script>
