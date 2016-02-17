package br.com.solutiolicita.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ricardocaldeira
 */
@FacesConverter(value = "cpfConverter")
public class CPFConverter implements Converter{
    
    /**
     * Convertendo CPF com mascara (111.111.111-11) em CPF sem mascara
     * (11111111111).
     *
     * @param context
     * @param component
     * @param value
     * @return
     * @throws ConverterException
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        String cpf = value;
        if (value != null && !"".equals(value)) {
            cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");
        }
        return cpf;
    }

    /**
     * Converter CPF sem mascara (11111111111) em CPF com mascara
     * (111.111.111-11)
     *
     * @param context
     * @param component
     * @param value
     * @return
     * @throws ConverterException
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {

        String cpf = (String) value;
        if (cpf != null && cpf.length() == 11) {
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        }

        return cpf;
    }
    
}
