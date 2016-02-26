package br.com.solutiolicita.util;

import br.com.solutiolicita.modelos.Pregao;
import br.com.solutiolicita.persistencia.DaoPregao;
import java.io.Serializable;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ricardocaldeira
 */
//@FacesConverter("pregaoConverter")
@Named
public class PregaoConverter implements Converter,Serializable{
    
    @Inject
    private DaoPregao daoPregao;
    
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        /**
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
        **/
        
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        Long id = Long.parseLong(value);
        
        return daoPregao.buscarPorId(id);
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        /**
        if (value != null
                && !"".equals(value)) {

            Pregao entity = (Pregao) value;

            // adiciona item como atributo do componente
            this.addAttribute(component, entity);
            
            Long codigo = entity.getId();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }

        return (String) value;
        **/
        
        if (value == null) {
            return null;
        }
        
        Long id = ((Pregao) value).getId();
        
        return (id != null) ? id.toString() : null;
    }

    protected void addAttribute(UIComponent component, Pregao o) {
        String key = o.getId().toString(); // codigo da empresa como chave neste caso
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

}
