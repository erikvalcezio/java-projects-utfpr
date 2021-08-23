package br.eti.arthurgregorio.contadorvendas;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends AbstractBeanField<String, LocalDate> {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
    @Override
    protected LocalDate convert(String value) {        
        return LocalDate.parse(value , formatter);
    }
}
