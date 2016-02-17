package br.com.solutiolicita.util;

import br.com.solutiolicita.modelos.InstituicaoLicitadora;
import java.io.Serializable;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ricardocaldeira
 */
@FacesConverter("instituicaoLicitadoraConverter")
public class InstituicaoLicitadoraConverter implements Converter,Serializable{
    
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null
                && !"".equals(value)) {

            InstituicaoLicitadora entity = (InstituicaoLicitadora) value;

            // adiciona item como atributo do componente
            this.addAttribute(component, entity);
            
            Long codigo = entity.getId();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }

        return (String) value;
    }

    protected void addAttribute(UIComponent component, InstituicaoLicitadora o) {
        String key = o.getId().toString(); // codigo da empresa como chave neste caso
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

}
