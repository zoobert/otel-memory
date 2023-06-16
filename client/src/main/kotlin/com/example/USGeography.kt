package com.example

import com.example.model.CreateGeographyRequest
import com.example.model.GeographyClassification

object USGeography {
    fun createGeography(
        classification: GeographyClassification,
        name: String,
        code: String? = null
    ) = CreateGeographyRequest(
        parentId = "0",
        name = name,
        isoCode = code
    )

    val UNITED_STATES = createGeography(
        classification = GeographyClassification.COUNTRY,
        name = "UNITED STATES",
        code = "US"
    )

    val ALABAMA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "ALABAMA",
        code = "AL"
    )
    val ALASKA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "ALASKA",
        code = "AK"
    )
    val ARIZONA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "ARIZONA",
        code = "AZ"
    )
    val ARKANSAS = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "ARKANSAS",
        code = "AR"
    )
    val CALIFORNIA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "CALIFORNIA",
        code = "CA"
    )
    val COLORADO = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "COLORADO",
        code = "CO"
    )
    val CONNECTICUT = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "CONNECTICUT",
        code = "CT"
    )
    val DELAWARE = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "DELAWARE",
        code = "DE"
    )
    val FLORIDA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "FLORIDA",
        code = "FL"
    )
    val GEORGIA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "GEORGIA",
        code = "GA"
    )
    val HAWAII = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "HAWAII",
        code = "HI"
    )
    val IDAHO =
        createGeography(classification = GeographyClassification.DIVISION, name = "IDAHO", code = "ID")
    val ILLINOIS = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "ILLINOIS",
        code = "IL"
    )
    val INDIANA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "INDIANA",
        code = "IN"
    )
    val IOWA =
        createGeography(classification = GeographyClassification.DIVISION, name = "IOWA", code = "IA")
    val KANSAS = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "KANSAS",
        code = "KS"
    )
    val KENTUCKY = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "KENTUCKY",
        code = "KY"
    )
    val LOUISIANA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "LOUISIANA",
        code = "LA"
    )
    val MAINE =
        createGeography(classification = GeographyClassification.DIVISION, name = "MAINE", code = "ME")
    val MARYLAND = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "MARYLAND",
        code = "MD"
    )
    val MASSACHUSETTS =
        createGeography(
            classification = GeographyClassification.DIVISION,
            name = "MASSACHUSETTS",
            code = "MA"
        )
    val MICHIGAN = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "MICHIGAN",
        code = "MI"
    )
    val MINNESOTA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "MINNESOTA",
        code = "MN"
    )
    val MISSISSIPPI = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "MISSISSIPPI",
        code = "MS"
    )
    val MISSOURI = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "MISSOURI",
        code = "MO"
    )
    val MONTANA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "MONTANA",
        code = "MT"
    )
    val NEBRASKA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "NEBRASKA",
        code = "NE"
    )
    val NEVADA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "NEVADA",
        code = "NV"
    )
    val NEW_HAMPSHIRE =
        createGeography(
            classification = GeographyClassification.DIVISION,
            name = "NEW HAMPSHIRE",
            code = "NH"
        )
    val NEW_JERSEY = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "NEW JERSEY",
        code = "NJ"
    )
    val NEW_MEXICO = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "NEW MEXICO",
        code = "NM"
    )
    val NEW_YORK = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "NEW YORK",
        code = "NY"
    )
    val NORTH_CAROLINA =
        createGeography(
            classification = GeographyClassification.DIVISION,
            name = "NORTH CAROLINA",
            code = "NC"
        )
    val NORTH_DAKOTA =
        createGeography(
            classification = GeographyClassification.DIVISION,
            name = "NORTH DAKOTA",
            code = "ND"
        )
    val OHIO =
        createGeography(classification = GeographyClassification.DIVISION, name = "OHIO", code = "OH")
    val OKLAHOMA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "OKLAHOMA",
        code = "OK"
    )
    val OREGON = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "OREGON",
        code = "OR"
    )
    val PENNSYLVANIA =
        createGeography(
            classification = GeographyClassification.DIVISION,
            name = "PENNSYLVANIA",
            code = "PA"
        )
    val RHODE_ISLAND =
        createGeography(
            classification = GeographyClassification.DIVISION,
            name = "RHODE ISLAND",
            code = "RI"
        )
    val SOUTH_CAROLINA =
        createGeography(
            classification = GeographyClassification.DIVISION,
            name = "SOUTH CAROLINA",
            code = "SC"
        )
    val SOUTH_DAKOTA =
        createGeography(
            classification = GeographyClassification.DIVISION,
            name = "SOUTH DAKOTA",
            code = "SD"
        )
    val TENNESSEE = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "TENNESSEE",
        code = "TN"
    )
    val TEXAS =
        createGeography(classification = GeographyClassification.DIVISION, name = "TEXAS", code = "TX")
    val UTAH =
        createGeography(classification = GeographyClassification.DIVISION, name = "UTAH", code = "UT")
    val VERMONT = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "VERMONT",
        code = "VT"
    )
    val VIRGINIA = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "VIRGINIA",
        code = "VA"
    )
    val WASHINGTON = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "WASHINGTON",
        code = "WA"
    )
    val WEST_VIRGINIA =
        createGeography(
            classification = GeographyClassification.DIVISION,
            name = "WEST VIRGINIA",
            code = "WV"
        )
    val WISCONSIN = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "WISCONSIN",
        code = "WI"
    )
    val WYOMING = createGeography(
        classification = GeographyClassification.DIVISION,
        name = "WYOMING",
        code = "WY"
    )

    val US_STATES = listOf(
        ALABAMA,
        ALASKA,
        ARIZONA,
        ARKANSAS,
        CALIFORNIA,
        COLORADO,
        CONNECTICUT,
        DELAWARE,
        FLORIDA,
        GEORGIA,
        HAWAII,
        IDAHO,
        ILLINOIS,
        INDIANA,
        IOWA,
        KANSAS,
        KENTUCKY,
        LOUISIANA,
        MAINE,
        MARYLAND,
        MASSACHUSETTS,
        MICHIGAN,
        MINNESOTA,
        MISSISSIPPI,
        MISSOURI,
        MONTANA,
        NEBRASKA,
        NEVADA,
        NEW_HAMPSHIRE,
        NEW_JERSEY,
        NEW_MEXICO,
        NEW_YORK,
        NORTH_CAROLINA,
        NORTH_DAKOTA,
        OHIO,
        OKLAHOMA,
        OREGON,
        PENNSYLVANIA,
        RHODE_ISLAND,
        SOUTH_CAROLINA,
        SOUTH_DAKOTA,
        TENNESSEE,
        TEXAS,
        UTAH,
        VERMONT,
        VIRGINIA,
        WASHINGTON,
        WEST_VIRGINIA,
        WISCONSIN,
        WYOMING
    )
}
