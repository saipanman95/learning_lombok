$(document).ready(function(){
    highlightProblems();
});

function highlightProblems(){
    var overallProblems = $('.problem.count').text();
    if (overallProblems > 0) {
        $('.problem.count').parents('div:eq(1)').addClass('overallProblem');
    } else {
        $('.problem.count').parents('div:eq(1)').removeClass('overallProblem');
    }
}