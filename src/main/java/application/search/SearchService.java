package application.search;

import application.database.Apartment;
import application.database.Availability;
import application.database.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.util.*;
import javax.persistence.Query;

/**
 * Created by thanasis on 16/8/2017.
 */
@Service
public class SearchService {

    @Autowired
    EntityManager entityManager;


// page : current page from the result list (STARTS FROM 1)
    public Result getResultList(Search search,int page){
        String queryStr = search.buildQuery();
        Query query=entityManager.createNativeQuery(queryStr,Apartment.class);
        search.passParameter(queryStr,query);
        List<Apartment> apartmentsResult = query.getResultList();
        System.out.println(apartmentsResult.size());

        //////chech the availability
        String queryStrAvailability="Select * from availability where availability.apartment_apartment_id=:aparId and (:startDate between availability.from_av and availability.to_av) and (:endDate between availability.from_av and availability.to_av)";
        Query queryAvailability=entityManager.createNativeQuery(queryStrAvailability,Availability.class);

        String queryStrBook="Select * from book_info where book_info.apartment=:aparId and (:startDate between book_info.book_in and book_info.book_out) and (:endDate between book_info.book_in and book_info.book_out)";
        Query queryBook=entityManager.createNativeQuery(queryStrBook, BookInfo.class);

        List<Apartment> apartmentsResultsFinal=new ArrayList<Apartment>();
        for (Apartment oneApartment :
                apartmentsResult) {
            queryAvailability.setParameter("aparId",oneApartment.getApartmentId());
            queryAvailability.setParameter("startDate",search.getFromDate());
            queryAvailability.setParameter("endDate",search.getToDate());
            queryBook.setParameter("aparId",oneApartment.getApartmentId());
            queryBook.setParameter("startDate",search.getFromDate());
            queryBook.setParameter("endDate",search.getToDate());


            List<Availability> availabilityResult = queryAvailability.getResultList();
            List<BookInfo> bookResult = queryBook.getResultList();

            if(availabilityResult.size()<1 || bookResult.size()>0){
                System.out.println("not available");
                System.out.println(oneApartment.toString());
            }else{
                System.out.println("Available");
                System.out.println(oneApartment.toString());
                apartmentsResultsFinal.add(oneApartment);
            }
        }
        System.out.println("\nend of results\n");
        Result searchResults = new Result(apartmentsResult,page);
        return searchResults;

    }
}
