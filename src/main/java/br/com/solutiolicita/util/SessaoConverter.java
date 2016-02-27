/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.solutiolicita.util;

import br.com.solutiolicita.modelos.Sessao;
import br.com.solutiolicita.persistencia.DaoIF;
import java.io.Serializable;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Matheus Oliveira
 */
@Named
public class SessaoConverter implements Converter, Serializable{
    
    @Inject
    private DaoIF<Sessao> daoSessao;
    
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        Long id = Long.parseLong(value);
        
        return daoSessao.buscarPorId(id);
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value == null) {
            return null;
        }
        
        Long id = ((Sessao) value).getId();
        
        return (id != null) ? id.toString() : null;
    }

    protected void addAttribute(UIComponent component, Sessao o) {
        String key = o.getId().toString(); // codigo da empresa como chave neste caso
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
    
}
