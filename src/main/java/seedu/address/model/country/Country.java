package seedu.address.model.country;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.Locale;

/**
 * Represents a Person's country in the address book.
 * Guarantees: immutable; is always valid
 */
public class Country {
    public static final String MESSAGE_CONSTRAINTS = "Country entered should be in the list below:\n"
                                                     + "Afghanistan\n"
                                                     + "Åland Islands\n"
                                                     + "Albania\n"
                                                     + "Algeria\n"
                                                     + "American Samoa\n"
                                                     + "Andorra\n"
                                                     + "Angola\n"
                                                     + "Anguilla\n"
                                                     + "Antarctica\n"
                                                     + "Antigua & Barbuda\n"
                                                     + "Argentina\n"
                                                     + "Armenia\n"
                                                     + "Aruba\n"
                                                     + "Australia\n"
                                                     + "Austria\n"
                                                     + "Azerbaijan\n"
                                                     + "Bahamas\n"
                                                     + "Bahrain\n"
                                                     + "Bangladesh\n"
                                                     + "Barbados\n"
                                                     + "Belarus\n"
                                                     + "Belgium\n"
                                                     + "Belize\n"
                                                     + "Benin\n"
                                                     + "Bermuda\n"
                                                     + "Bhutan\n"
                                                     + "Bolivia\n"
                                                     + "Bosnia & Herzegovina\n"
                                                     + "Botswana\n"
                                                     + "Bouvet Island\n"
                                                     + "Brazil\n"
                                                     + "British Indian Ocean Territory\n"
                                                     + "British Virgin Islands\n"
                                                     + "Brunei\n"
                                                     + "Bulgaria\n"
                                                     + "Burkina Faso\n"
                                                     + "Burundi\n"
                                                     + "Cambodia\n"
                                                     + "Cameroon\n"
                                                     + "Canada\n"
                                                     + "Cape Verde\n"
                                                     + "Caribbean Netherlands\n"
                                                     + "Cayman Islands\n"
                                                     + "Central African Republic\n"
                                                     + "Chad\n"
                                                     + "Chile\n"
                                                     + "China\n"
                                                     + "Christmas Island\n"
                                                     + "Cocos (Keeling) Islands\n"
                                                     + "Colombia\n"
                                                     + "Comoros\n"
                                                     + "Congo - Brazzaville\n"
                                                     + "Congo - Kinshasa\n"
                                                     + "Cook Islands\n"
                                                     + "Costa Rica\n"
                                                     + "Côte d’Ivoire\n"
                                                     + "Croatia\n"
                                                     + "Cuba\n"
                                                     + "Curaçao\n"
                                                     + "Cyprus\n"
                                                     + "Czechia\n"
                                                     + "Denmark\n"
                                                     + "Djibouti\n"
                                                     + "Dominica\n"
                                                     + "Dominican Republic\n"
                                                     + "Ecuador\n"
                                                     + "Egypt\n"
                                                     + "El Salvador\n"
                                                     + "Equatorial Guinea\n"
                                                     + "Eritrea\n"
                                                     + "Estonia\n"
                                                     + "Ethiopia\n"
                                                     + "Falkland Islands\n"
                                                     + "Faroe Islands\n"
                                                     + "Fiji\n"
                                                     + "Finland\n"
                                                     + "France\n"
                                                     + "French Guiana\n"
                                                     + "French Polynesia\n"
                                                     + "French Southern Territories\n"
                                                     + "Gabon\n"
                                                     + "Gambia\n"
                                                     + "Georgia\n"
                                                     + "Germany\n"
                                                     + "Ghana\n"
                                                     + "Gibraltar\n"
                                                     + "Greece\n"
                                                     + "Greenland\n"
                                                     + "Grenada\n"
                                                     + "Guadeloupe\n"
                                                     + "Guam\n"
                                                     + "Guatemala\n"
                                                     + "Guernsey\n"
                                                     + "Guinea\n"
                                                     + "Guinea-Bissau\n"
                                                     + "Guyana\n"
                                                     + "Haiti\n"
                                                     + "Heard & McDonald Islands\n"
                                                     + "Honduras\n"
                                                     + "Hong Kong SAR China\n"
                                                     + "Hungary\n"
                                                     + "Iceland\n"
                                                     + "India\n"
                                                     + "Indonesia\n"
                                                     + "Iran\n"
                                                     + "Iraq\n"
                                                     + "Ireland\n"
                                                     + "Isle of Man\n"
                                                     + "Israel\n"
                                                     + "Italy\n"
                                                     + "Jamaica\n"
                                                     + "Japan\n"
                                                     + "Jersey\n"
                                                     + "Jordan\n"
                                                     + "Kazakhstan\n"
                                                     + "Kenya\n"
                                                     + "Kiribati\n"
                                                     + "Kuwait\n"
                                                     + "Kyrgyzstan\n"
                                                     + "Laos\n"
                                                     + "Latvia\n"
                                                     + "Lebanon\n"
                                                     + "Lesotho\n"
                                                     + "Liberia\n"
                                                     + "Libya\n"
                                                     + "Liechtenstein\n"
                                                     + "Lithuania\n"
                                                     + "Luxembourg\n"
                                                     + "Macau SAR China\n"
                                                     + "Macedonia\n"
                                                     + "Madagascar\n"
                                                     + "Malawi\n"
                                                     + "Malaysia\n"
                                                     + "Maldives\n"
                                                     + "Mali\n"
                                                     + "Malta\n"
                                                     + "Marshall Islands\n"
                                                     + "Martinique\n"
                                                     + "Mauritania\n"
                                                     + "Mauritius\n"
                                                     + "Mayotte\n"
                                                     + "Mexico\n"
                                                     + "Micronesia\n"
                                                     + "Moldova\n"
                                                     + "Monaco\n"
                                                     + "Mongolia\n"
                                                     + "Montenegro\n"
                                                     + "Montserrat\n"
                                                     + "Morocco\n"
                                                     + "Mozambique\n"
                                                     + "Myanmar (Burma)\n"
                                                     + "Namibia\n"
                                                     + "Nauru\n"
                                                     + "Nepal\n"
                                                     + "Netherlands\n"
                                                     + "New Caledonia\n"
                                                     + "New Zealand\n"
                                                     + "Nicaragua\n"
                                                     + "Niger\n"
                                                     + "Nigeria\n"
                                                     + "Niue\n"
                                                     + "Norfolk Island\n"
                                                     + "North Korea\n"
                                                     + "Northern Mariana Islands\n"
                                                     + "Norway\n"
                                                     + "Oman\n"
                                                     + "Pakistan\n"
                                                     + "Palau\n"
                                                     + "Palestinian Territories\n"
                                                     + "Panama\n"
                                                     + "Papua New Guinea\n"
                                                     + "Paraguay\n"
                                                     + "Peru\n"
                                                     + "Philippines\n"
                                                     + "Pitcairn Islands\n"
                                                     + "Poland\n"
                                                     + "Portugal\n"
                                                     + "Puerto Rico\n"
                                                     + "Qatar\n"
                                                     + "Réunion\n"
                                                     + "Romania\n"
                                                     + "Russia\n"
                                                     + "Rwanda\n"
                                                     + "Samoa\n"
                                                     + "San Marino\n"
                                                     + "São Tomé & Príncipe\n"
                                                     + "Saudi Arabia\n"
                                                     + "Senegal\n"
                                                     + "Serbia\n"
                                                     + "Seychelles\n"
                                                     + "Sierra Leone\n"
                                                     + "Singapore\n"
                                                     + "Sint Maarten\n"
                                                     + "Slovakia\n"
                                                     + "Slovenia\n"
                                                     + "Solomon Islands\n"
                                                     + "Somalia\n"
                                                     + "South Africa\n"
                                                     + "South Georgia & South Sandwich Islands\n"
                                                     + "South Korea\n"
                                                     + "South Sudan\n"
                                                     + "Spain\n"
                                                     + "Sri Lanka\n"
                                                     + "St. Barthélemy\n"
                                                     + "St. Helena\n"
                                                     + "St. Kitts & Nevis\n"
                                                     + "St. Lucia\n"
                                                     + "St. Martin\n"
                                                     + "St. Pierre & Miquelon\n"
                                                     + "St. Vincent & Grenadines\n"
                                                     + "Sudan\n"
                                                     + "Suriname\n"
                                                     + "Svalbard & Jan Mayen\n"
                                                     + "Swaziland\n"
                                                     + "Sweden\n"
                                                     + "Switzerland\n"
                                                     + "Syria\n"
                                                     + "Taiwan\n"
                                                     + "Tajikistan\n"
                                                     + "Tanzania\n"
                                                     + "Thailand\n"
                                                     + "Timor-Leste\n"
                                                     + "Togo\n"
                                                     + "Tokelau\n"
                                                     + "Tonga\n"
                                                     + "Trinidad & Tobago\n"
                                                     + "Tunisia\n"
                                                     + "Turkey\n"
                                                     + "Turkmenistan\n"
                                                     + "Turks & Caicos Islands\n"
                                                     + "Tuvalu\n"
                                                     + "U.S. Outlying Islands\n"
                                                     + "U.S. Virgin Islands\n"
                                                     + "Uganda\n"
                                                     + "Ukraine\n"
                                                     + "United Arab Emirates\n"
                                                     + "United Kingdom\n"
                                                     + "United States\n"
                                                     + "Uruguay\n"
                                                     + "Uzbekistan\n"
                                                     + "Vatican City\n"
                                                     + "Venezuela\n"
                                                     + "Vietnam\n"
                                                     + "Vanuatu\n"
                                                     + "Wallis & Futuna\n"
                                                     + "Western Sahara\n"
                                                     + "Yemen\n"
                                                     + "Zambia\n"
                                                     + "Zimbabwe";
    public final String value;

    /**
     * Constructs a {@code Country}
     *
     * @param country A valid country.
     */
    public Country(String country) {
        requireNonNull(country);
        checkArgument(isValidCountry(country));
        value = country;
    }

    /**
     * Returns if a given string is a valid country.
     */
    public static boolean isValidCountry(String test) {
        return isEmpty(test) || Arrays.stream(Locale.getISOCountries())
                                      .anyMatch(x -> new Locale("", x).getDisplayCountry().equals(test));
    }

    private static boolean isEmpty(String test) {
        return test.equals("");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Country // instanceof handles nulls
                && value.equals(((Country) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
