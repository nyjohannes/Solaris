package solaris.paivamaara;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Paivamaara {

    /**
     * Calendar-muotoinen olio Paivamaara-olion sisällä.
     */
    private Calendar paivamaara;
    /**
     * DateFormat-olio, joka määrittää oikean päivämäärän muodon.
     */
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Paivamaara-olion konstruktori. Konstruktori luo Calendar-luokan olion, ja
     * asettaa sen ominaisuudeksi käyttäjän Stringinä antaman syötteen, jonka
     * onkoPaivaHyva-metodi on tarkistanut mahdolliseksi.
     *
     * @param pvm String-muotoinen merkkijono päivämäärästä.
     * @throws ParseException
     */
    public Paivamaara(String pvm) throws ParseException {

        this.paivamaara = paivamaara.getInstance();
        this.paivamaara.setTime(df.parse(pvm));

    }

    public Calendar getPaiva() {

        return this.paivamaara;

    }

    /**
     * Metodi laskee Paivamaara-olion paivamaarasta kuinka monta päivää on
     * kulunut edellisestä talvipäivänseisauksesta.
     *
     * @return double-luku päivistä talvipäivänseisauksesta
     */
    public double getPaiviaTalvipaivanSeisauksesta() {
        int monesPaivaVuodesta = this.paivamaara.get(Calendar.DAY_OF_YEAR);
        double paivia = 365 - (356 - monesPaivaVuodesta);
        if (monesPaivaVuodesta > 356) {
            paivia = monesPaivaVuodesta - 356;
        }
        return paivia;

    }

    /**
     * Metodi kertoo onko annettu vuosi karkausvuosi ja palauttaa vastauksena
     * boolean-totuusarvon.
     *
     * @param vuosi int-muotoinen vuosiluku.
     * @return Totuusarvo onko vuosi karkausvuosi.
     */
    public static boolean onkoKarkausvuosi(int vuosi) {

        boolean onKarkausVuosi = ((vuosi % 4 == 0) && (vuosi % 100 != 0) || (vuosi % 400 == 0));

        if (vuosi % 4 != 0) {
            return false;
        } else if (vuosi % 400 == 0) {
            return true;
        } else if (vuosi % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Metodi kertoo onko käyttäjän syötteellä antama päivämäärä mahdollinen
     * päivämäärä ja onko se halutussa dd.mm.yyyy -muodossa.
     *
     * @param pvm String-muotoinen käyttäjän antama syöte
     * @param dateFromat String-muotoinen ennalta määrätty formaatti
     * päivämäärille. dd.mm.yyyy
     * @return Totuusarvo onko annettu päivämäärä mahdollinen ja oikeassa
     * muodossa.
     */
    public static boolean onkoPaivaHyva(String pvm, String dateFromat) {

        if (pvm == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(pvm);

        } catch (ParseException e) {
            return false;
        }

        return true;
    }
    
    public String toString(){
    
    return this.paivamaara.get(Calendar.DATE) + "." + (this.paivamaara.get(Calendar.MONTH)+1) + "." + this.paivamaara.get(Calendar.YEAR);
    }

}
