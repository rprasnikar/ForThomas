/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPanelKonfigER.java
 *
 * Created on 25.10.2010, 17:33:28
 */

package at.jollydays.booking.ui;

import at.jollydays.booking.bo.BuhaArea;
import at.jollydays.booking.bo.BuhaBooking;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.RollbackException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jdesktop.beansbinding.AbstractBindingListener;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.PropertyStateEvent;

/**
 *
 * @author Gunter Reinitzer
 */
public class JPanelKonfigER extends javax.swing.JPanel {

    /** Creates new form JPanelKonfigER */
    public JPanelKonfigER() {
        initComponents();

                // tracking table selection
        jTableArea.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
            @Override
                public void valueChanged(ListSelectionEvent e) {
                    firePropertyChange("areaRecordSelected", !isAreaRecordSelected(), isAreaRecordSelected());
                }
            });
        jTableBooking.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
            @Override
                public void valueChanged(ListSelectionEvent e) {
                    firePropertyChange("bookingRecordSelected", !isBookingRecordSelected(), isBookingRecordSelected());
                }
            });

        // tracking changes to save
        bindingGroup.addBindingListener(new AbstractBindingListener() {
            @Override
            public void targetChanged(Binding binding, PropertyStateEvent event) {
                // save action observes saveNeeded property
                setSaveNeeded(true);
            }
        });

        // have a transaction started
        JollydaysBuchhaltungPUJollydaysEntityManager.getTransaction().begin();

    }

    
    public boolean isSaveNeeded() {
        return saveNeeded;
    }

    private void setSaveNeeded(boolean saveNeeded) {
        if (saveNeeded != this.saveNeeded) {
            this.saveNeeded = saveNeeded;
            //org.​jdesktop.​application.​AbstractBean
            //javax.​swing.​JComponent
            firePropertyChange("saveNeeded", !saveNeeded, saveNeeded); 
        }
    }

    public boolean isAreaRecordSelected() {
        return jTableArea.getSelectedRow() != -1;
    }

    public boolean isBookingRecordSelected() {
        return jTableBooking.getSelectedRow() != -1;
    }

    @Action
    public void newAreaRecord() {
        BuhaArea B = new BuhaArea(); 
        JollydaysBuchhaltungPUJollydaysEntityManager.persist(B);
        buhaAreaList.add(B);
        int row = buhaAreaList.size()-1;
        jTableArea.setRowSelectionInterval(row, row);
        jTableArea.scrollRectToVisible(jTableArea.getCellRect(row, 0, true));
        setSaveNeeded(true);
    }

    @Action(enabledProperty = "areaRecordSelected")
    public void deleteAreaRecord() {
        int[] selected = jTableArea.getSelectedRows();
        List<BuhaArea> toRemove = new ArrayList<BuhaArea>(selected.length);
        for (int idx=0; idx<selected.length; idx++) {
            BuhaArea B = buhaAreaList.get(jTableArea.convertRowIndexToModel(selected[idx]));
            toRemove.add(B);
            JollydaysBuchhaltungPUJollydaysEntityManager.remove(B);
        }
        buhaBookingList.removeAll(toRemove);
        setSaveNeeded(true);
    }

    @Action(enabledProperty = "areaRecordSelected")
    public void newBookingRecord() {
        int index = jTableArea.getSelectedRow();
        BuhaArea B = buhaAreaList.get(jTableArea.convertRowIndexToModel(index));
        Collection<BuhaBooking> bs = B.getBuhaBookingCollection();
        if (bs == null) {
            bs = new LinkedList<BuhaBooking>();
            B.setBuhaBookingCollection((List)bs);
        }
        BuhaBooking b = new BuhaBooking();
        JollydaysBuchhaltungPUJollydaysEntityManager.persist(b);
        b.setBuhaArea(B);
        bs.add(b);
        jTableArea.clearSelection();
        jTableArea.setRowSelectionInterval(index, index);
        int row = bs.size()-1;
        jTableBooking.setRowSelectionInterval(row, row);
        jTableBooking.scrollRectToVisible(jTableBooking.getCellRect(row, 0, true));
        setSaveNeeded(true);
    }

    @Action(enabledProperty = "bookingRecordSelected")
    public void deleteBookingRecord() {
        int index = jTableArea.getSelectedRow();
        BuhaArea B = buhaAreaList.get(jTableArea.convertRowIndexToModel(index));
        Collection<BuhaBooking> bs = B.getBuhaBookingCollection();
        int[] selected = jTableBooking.getSelectedRows();
        List<BuhaBooking> toRemove = new ArrayList<BuhaBooking>(selected.length);
        for (int idx=0; idx<selected.length; idx++) {
            selected[idx] = jTableBooking.convertRowIndexToModel(selected[idx]);
            int count = 0;
            Iterator<BuhaBooking> iter = bs.iterator();
            while (count++ < selected[idx]) iter.next();
            BuhaBooking b = iter.next();
            toRemove.add(b);
            JollydaysBuchhaltungPUJollydaysEntityManager.remove(b);
        }
        bs.removeAll(toRemove);
        jTableArea.clearSelection();
        jTableArea.setRowSelectionInterval(index, index);
        setSaveNeeded(true);
    }

    @Action(enabledProperty = "saveNeeded")
    public Task save() {
        return new SaveTask(MainUI.getApplication());
    }

    private class SaveTask extends Task {
        SaveTask(org.jdesktop.application.Application app) {
            super(app);
        }
        @Override protected Void doInBackground() {
            try {
                JollydaysBuchhaltungPUJollydaysEntityManager.getTransaction().commit();
                JollydaysBuchhaltungPUJollydaysEntityManager.getTransaction().begin();
            } catch (RollbackException rex) {
                rex.printStackTrace();
                JollydaysBuchhaltungPUJollydaysEntityManager.getTransaction().begin();
                List<BuhaArea> merged = new ArrayList<BuhaArea>(buhaAreaList.size());
                for (BuhaArea B : buhaAreaList) {
                    merged.add(JollydaysBuchhaltungPUJollydaysEntityManager.merge(B));
                }
                buhaAreaList.clear();
                buhaAreaList.addAll(merged);
            }
            return null;
        }
        @Override protected void finished() {
            setSaveNeeded(false);
        }
    } 

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        JollydaysBuchhaltungPUJollydaysEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("JollydaysBuchhaltungPUJollydays").createEntityManager();
        buhaAreaQuery = java.beans.Beans.isDesignTime() ? null : JollydaysBuchhaltungPUJollydaysEntityManager.createQuery("SELECT b FROM BuhaArea b");
        buhaAreaList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : buhaAreaQuery.getResultList();
        buhaAreaQuery1 = java.beans.Beans.isDesignTime() ? null : JollydaysBuchhaltungPUJollydaysEntityManager.createQuery("SELECT b FROM BuhaArea b");
        buhaAreaList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : buhaAreaQuery1.getResultList();
        buhaBookingQuery = java.beans.Beans.isDesignTime() ? null : JollydaysBuchhaltungPUJollydaysEntityManager.createQuery("SELECT b FROM BuhaBooking b");
        buhaBookingList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : buhaBookingQuery.getResultList();
        buhaFilterQuery = java.beans.Beans.isDesignTime() ? null : JollydaysBuchhaltungPUJollydaysEntityManager.createQuery("SELECT b FROM BuhaFilter b");
        buhaFilterList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : buhaFilterQuery.getResultList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableArea = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBooking = new javax.swing.JTable();
        jButtonDelArea = new javax.swing.JButton();
        jButtonNewArea = new javax.swing.JButton();
        jButtonSaveBooking = new javax.swing.JButton();
        jButtonRefreshBooking = new javax.swing.JButton();
        jButtonDelBooking = new javax.swing.JButton();
        jButtonNewBooking = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableFilter = new javax.swing.JTable();
        jButtonSaveFilter = new javax.swing.JButton();
        jButtonRefreshFilter = new javax.swing.JButton();
        jButtonDelFilter = new javax.swing.JButton();
        jButtonNewFilter = new javax.swing.JButton();

        setName("Form"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTableArea.setName("jTableArea"); // NOI18N

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, buhaAreaList, jTableArea);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${description}"));
        columnBinding.setColumnName("Description");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("Id");
        columnBinding.setColumnClass(Integer.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(jTableArea);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTableBooking.setName("jTableBooking"); // NOI18N

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, buhaBookingList, jTableBooking);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${kontosoll}"));
        columnBinding.setColumnName("Konto Soll");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${kontohaben}"));
        columnBinding.setColumnName("Kontohaben");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${debitor}"));
        columnBinding.setColumnName("Debitor");
        columnBinding.setColumnClass(Short.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${creditor}"));
        columnBinding.setColumnName("Creditor");
        columnBinding.setColumnClass(Short.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${buchungscode}"));
        columnBinding.setColumnName("Buchungscode");
        columnBinding.setColumnClass(Short.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${steuersatz}"));
        columnBinding.setColumnName("Steuersatz");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${gutscheinfilter}"));
        columnBinding.setColumnName("Gutscheinfilter");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bookingtype}"));
        columnBinding.setColumnName("Bookingtype");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${buhaCountry}"));
        columnBinding.setColumnName("Buha Country");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${webCountry}"));
        columnBinding.setColumnName("Web Country");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${description}"));
        columnBinding.setColumnName("Description");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTableBooking);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(at.jollydays.booking.ui.MainUI.class).getContext().getResourceMap(JPanelKonfigER.class);
        jTableBooking.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title1")); // NOI18N
        jTableBooking.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title2")); // NOI18N
        jTableBooking.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title3")); // NOI18N
        jTableBooking.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title4")); // NOI18N
        jTableBooking.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title5")); // NOI18N
        jTableBooking.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title6")); // NOI18N
        jTableBooking.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title7")); // NOI18N
        jTableBooking.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title8")); // NOI18N
        jTableBooking.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title9")); // NOI18N
        jTableBooking.getColumnModel().getColumn(10).setHeaderValue(resourceMap.getString("jTableBooking.columnModel.title10")); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(at.jollydays.booking.ui.MainUI.class).getContext().getActionMap(JPanelKonfigER.class, this);
        jButtonDelArea.setAction(actionMap.get("deleteAreaRecord")); // NOI18N
        jButtonDelArea.setText(resourceMap.getString("jButtonDelArea.text")); // NOI18N
        jButtonDelArea.setName("jButtonDelArea"); // NOI18N

        jButtonNewArea.setAction(actionMap.get("newAreaRecord")); // NOI18N
        jButtonNewArea.setText(resourceMap.getString("jButtonNewArea.text")); // NOI18N
        jButtonNewArea.setName("jButtonNewArea"); // NOI18N

        jButtonSaveBooking.setText(resourceMap.getString("jButtonSaveBooking.text")); // NOI18N
        jButtonSaveBooking.setName("jButtonSaveBooking"); // NOI18N

        jButtonRefreshBooking.setText(resourceMap.getString("jButtonRefreshBooking.text")); // NOI18N
        jButtonRefreshBooking.setName("jButtonRefreshBooking"); // NOI18N

        jButtonDelBooking.setText(resourceMap.getString("jButtonDelBooking.text")); // NOI18N
        jButtonDelBooking.setName("jButtonDelBooking"); // NOI18N

        jButtonNewBooking.setText(resourceMap.getString("jButtonNewBooking.text")); // NOI18N
        jButtonNewBooking.setName("jButtonNewBooking"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTableFilter.setName("jTableFilter"); // NOI18N

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, buhaFilterList, jTableFilter);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${itemFrom}"));
        columnBinding.setColumnName("Item From");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${itemTo}"));
        columnBinding.setColumnName("Item To");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${gutscheinfilter}"));
        columnBinding.setColumnName("Gutscheinfilter");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${country}"));
        columnBinding.setColumnName("Country");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${description}"));
        columnBinding.setColumnName("Description");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane3.setViewportView(jTableFilter);
        jTableFilter.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTableFilter.columnModel.title0")); // NOI18N
        jTableFilter.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTableFilter.columnModel.title1")); // NOI18N
        jTableFilter.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTableFilter.columnModel.title2")); // NOI18N
        jTableFilter.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTableFilter.columnModel.title3")); // NOI18N
        jTableFilter.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTableFilter.columnModel.title4")); // NOI18N

        jButtonSaveFilter.setText(resourceMap.getString("jButtonSaveFilter.text")); // NOI18N
        jButtonSaveFilter.setName("jButtonSaveFilter"); // NOI18N

        jButtonRefreshFilter.setText(resourceMap.getString("jButtonRefreshFilter.text")); // NOI18N
        jButtonRefreshFilter.setName("jButtonRefreshFilter"); // NOI18N

        jButtonDelFilter.setText(resourceMap.getString("jButtonDelFilter.text")); // NOI18N
        jButtonDelFilter.setName("jButtonDelFilter"); // NOI18N

        jButtonNewFilter.setText(resourceMap.getString("jButtonNewFilter.text")); // NOI18N
        jButtonNewFilter.setName("jButtonNewFilter"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1043, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(605, 605, 605)
                .addComponent(jButtonNewArea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelArea))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(jButtonNewBooking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelBooking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRefreshBooking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSaveBooking))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1043, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1043, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(jButtonNewFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRefreshFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSaveFilter))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDelArea)
                    .addComponent(jButtonNewArea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSaveBooking)
                    .addComponent(jButtonRefreshBooking)
                    .addComponent(jButtonDelBooking)
                    .addComponent(jButtonNewBooking))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSaveFilter)
                    .addComponent(jButtonRefreshFilter)
                    .addComponent(jButtonDelFilter)
                    .addComponent(jButtonNewFilter))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager JollydaysBuchhaltungPUJollydaysEntityManager;
    private java.util.List<at.jollydays.booking.bo.BuhaArea> buhaAreaList;
    private java.util.List<at.jollydays.booking.bo.BuhaArea> buhaAreaList1;
    private javax.persistence.Query buhaAreaQuery;
    private javax.persistence.Query buhaAreaQuery1;
    private java.util.List<at.jollydays.booking.bo.BuhaBooking> buhaBookingList;
    private javax.persistence.Query buhaBookingQuery;
    private java.util.List<at.jollydays.booking.bo.BuhaFilter> buhaFilterList;
    private javax.persistence.Query buhaFilterQuery;
    private javax.swing.JButton jButtonDelArea;
    private javax.swing.JButton jButtonDelBooking;
    private javax.swing.JButton jButtonDelFilter;
    private javax.swing.JButton jButtonNewArea;
    private javax.swing.JButton jButtonNewBooking;
    private javax.swing.JButton jButtonNewFilter;
    private javax.swing.JButton jButtonRefreshBooking;
    private javax.swing.JButton jButtonRefreshFilter;
    private javax.swing.JButton jButtonSaveBooking;
    private javax.swing.JButton jButtonSaveFilter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableArea;
    private javax.swing.JTable jTableBooking;
    private javax.swing.JTable jTableFilter;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private boolean saveNeeded;

}
