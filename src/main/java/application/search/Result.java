package application.search;

import application.database.Apartment;

import java.util.List;

/**
 * Created by thanasis on 27/8/2017.
 */

//tou dineis times sto servise kai to kaneis return etoimo ston controller
public class Result {
    // total number of results (not just the current page)
    //    WE SUPOSE THAT EACH PAGE HAS 12 RESULTS

    private List<Apartment> searchResult;//the list with the results (only the 12max we ask for)
    private int curPage;//the cur page
    private int totalNumberOfResults;//the total number of result(not 12)
    private int totalNumberOfPages;//the total number of pages results numRes/12 (+1)

    public Result() {
    }

    public Result(List<Apartment> searchResult,int curPage) {
        if(searchResult.size()==0 || searchResult.size()<(curPage-1)*12+1){
            System.out.println("None");
            return;
        }
        int until,begin;
        begin=(curPage-1)*12;
        if(searchResult.size()%12==0){
            until=begin+12;
        }else {
            until=begin+(searchResult.size()%12);
        }
        this.searchResult=searchResult.subList(begin,until);
        this.curPage=curPage;
        this.totalNumberOfResults=searchResult.size();
        this.totalNumberOfPages=searchResult.size()/12 + 1;
//        antoistixa pernontas ta megethi klp dwse times kai sta alla!(12 ana selida)
    }

    public List<Apartment> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<Apartment> searchResult) {
        this.searchResult = searchResult;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalNumberOfResults() {
        return totalNumberOfResults;
    }

    public void setTotalNumberOfResults(int totalNumberOfResults) {
        this.totalNumberOfResults = totalNumberOfResults;
    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }
}
