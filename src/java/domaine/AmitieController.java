package domaine;

import entities.Amitie;
import domaine.util.JsfUtil;
import domaine.util.JsfUtil.PersistAction;
import service.AmitieFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "amitieController")
@SessionScoped
public class AmitieController implements Serializable {

    @EJB
    private service.AmitieFacade ejbFacade;
    private List<Amitie> items = null;
    private Amitie selected;

    public AmitieController() {
    }

    public Amitie getSelected() {
        return selected;
    }

    public void setSelected(Amitie selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getAmitiePK().setIdUser2(selected.getUser().getIdUser());
        selected.getAmitiePK().setIdUser1(selected.getUser1().getIdUser());
    }

    protected void initializeEmbeddableKey() {
        selected.setAmitiePK(new entities.AmitiePK());
    }

    private AmitieFacade getFacade() {
        return ejbFacade;
    }

    public Amitie prepareCreate() {
        selected = new Amitie();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AmitieCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AmitieUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AmitieDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Amitie> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Amitie> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Amitie> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Amitie.class)
    public static class AmitieControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AmitieController controller = (AmitieController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "amitieController");
            return controller.getFacade().find(getKey(value));
        }

        entities.AmitiePK getKey(String value) {
            entities.AmitiePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.AmitiePK();
            key.setIdUser1(Integer.parseInt(values[0]));
            key.setIdUser2(Integer.parseInt(values[1]));
            key.setDateAmitie(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(entities.AmitiePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdUser1());
            sb.append(SEPARATOR);
            sb.append(value.getIdUser2());
            sb.append(SEPARATOR);
            sb.append(value.getDateAmitie());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Amitie) {
                Amitie o = (Amitie) object;
                return getStringKey(o.getAmitiePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Amitie.class.getName()});
                return null;
            }
        }

    }

}
