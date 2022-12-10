package com.example.Helpers.Travels;

import com.example.HibernateOracle.Model.TravelCompanyEntity;

public class TravelConvert {

    public static int convertToEntityAttribute(TravelCompanyEntity travelCompany){
        return travelCompany.getTc_id();
    }

}
