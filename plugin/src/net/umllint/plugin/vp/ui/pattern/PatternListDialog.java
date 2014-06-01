package net.umllint.plugin.vp.ui.pattern;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.view.IDialog;
import com.vp.plugin.view.IDialogHandler;
import net.umllint.common.model.pattern.ULPattern;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class PatternListDialog implements IDialogHandler {

	private final Component _component;
	private IDialog _dialog;

    private List<ULPattern> patterns;

	public PatternListDialog(final java.util.List<ULPattern> patterns) {

        this.patterns = patterns;



   /*
		PatternTableModel lSimpleRelationshipTableModel;
		{
			Collection lCollection = new ArrayList(); // <IRelationship>
			{
				// from base TO opposite
				Iterator lIter = aModel.fromRelationshipIterator();
				while (lIter.hasNext()) {
					IRelationship lRelationship = (IRelationship) lIter.next();
					lCollection.add(lRelationship);
				}
			}
			{
				// FROM opposite to base
				Iterator lIter = aModel.toRelationshipIterator();
				while (lIter.hasNext()) {
					IRelationship lRelationship = (IRelationship) lIter.next();
					if (aModel.equals(lRelationship.getFrom())) {
						// ignore, it is SELF, already included in "from base to opposite"
					}
					else {
						lCollection.add(lRelationship);
					}
				}
			}
			
			IRelationship[] lRelationships = new IRelationship[lCollection.size()];
			lCollection.toArray(lRelationships);
			lSimpleRelationshipTableModel = new PatternTableModel(aModel, lRelationships);
		}

        PatternTableModel lEndRelationshipTableModel;
		{
			Collection lCollection = new ArrayList(); // <IRelationship>
			{
				// from base TO opposite
				Iterator lIter = aModel.fromRelationshipEndIterator();
				while (lIter.hasNext()) {
					IRelationshipEnd lRelationshipEnd = (IRelationshipEnd) lIter.next();
					IRelationship lRelationship = lRelationshipEnd.getEndRelationship();
					lCollection.add(lRelationship);
				}
			}
			{
				// FROM opposite to base
				Iterator lIter = aModel.toRelationshipEndIterator();
				while (lIter.hasNext()) {
					IRelationshipEnd lRelationshipEnd = (IRelationshipEnd) lIter.next();
					IRelationship lRelationship = lRelationshipEnd.getEndRelationship();
					if (aModel.equals(lRelationship.getFrom())) {
						// ignore, it is SELF, already included in "from base to opposite"
					}
					else {
						lCollection.add(lRelationship);
					}
				}
			}
			
			IRelationship[] lRelationships = new IRelationship[lCollection.size()];
			lCollection.toArray(lRelationships);
			lEndRelationshipTableModel = new PatternTableModel(aModel, lRelationships);
		}
		*/

        JLabel tableLabel = new JLabel("Pattern List:");
        PatternTableModel tableModel = new PatternTableModel(patterns);
        JTable tableTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(tableTable);
        //tableTable.getColumnModel().getColumn(PatternTableModel.Columns.PatternID).setCellRenderer(new PatternCellRenderer());
        //tableTable.getColumnModel().getColumn(PatternTableModel.Columns.PatternTitle).setCellRenderer(new PatternCellRenderer());
        //tableTable.getColumnModel().getColumn(PatternTableModel.Columns.PatternCategory).setCellRenderer(new PatternCellRenderer());
        //tableTable.getColumnModel().getColumn(PatternTableModel.Columns.PatternSeverity).setCellRenderer(new PatternCellRenderer());
        tableTable.getColumnModel().getColumn(PatternTableModel.Columns.View).setCellRenderer(new PatternCellRenderer());
        //lSimpleRelationshipTable.getColumnModel().getColumn(PatternTableModel.Columns.RelationshipType).setCellRenderer(new ModelTypeCellRenderer());

        JButton closeButton = new JButton("Close");
		

        /*
		JTable lSimpleRelationshipTable = new JTable(lSimpleRelationshipTableModel);
		JScrollPane lSimpleRelationshipScrollPane = new JScrollPane(lSimpleRelationshipTable);
		JTable lEndRelationshipTable = new JTable(lEndRelationshipTableModel);
		JScrollPane lEndRelationshipScrollPane = new JScrollPane(lEndRelationshipTable);
		
		JLabel lEndRelationshipLabel = new JLabel("End Relationships:");

		lSimpleRelationshipTable.getColumnModel().getColumn(PatternTableModel.Columns.RelationshipType).setCellRenderer(new ModelTypeCellRenderer());
		lSimpleRelationshipTable.getColumnModel().getColumn(PatternTableModel.Columns.Model).setCellRenderer(new ModelElementCellRenderer());
		lEndRelationshipTable.getColumnModel().getColumn(PatternTableModel.Columns.RelationshipType).setCellRenderer(new ModelTypeCellRenderer());
		lEndRelationshipTable.getColumnModel().getColumn(PatternTableModel.Columns.Model).setCellRenderer(new ModelElementCellRenderer());

		
		JButton lCloseButton = new JButton("Close");
		*/

		JPanel lPanel = new JPanel();
		{
			GridBagLayout lLayout = new GridBagLayout();
			lPanel.setLayout(lLayout);
			
			GridBagConstraints lCons = new GridBagConstraints();
			lCons.anchor = GridBagConstraints.CENTER;
			lCons.gridwidth = GridBagConstraints.REMAINDER;
			lLayout.setConstraints(closeButton, lCons);
			
			lCons.anchor = GridBagConstraints.WEST;
			lLayout.setConstraints(tableLabel, lCons);

			lCons.weightx = 1;
			lCons.weighty =1 ;
			lCons.fill = GridBagConstraints.BOTH;
			lCons.insets.bottom = 10;
			lLayout.setConstraints(tableScrollPane, lCons);
		}

        lPanel.add(tableLabel);
		lPanel.add(tableScrollPane);
		lPanel.add(closeButton);

		_component = lPanel;
		
		
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				_dialog.close();
			}
		});

        MouseListener patternListTableMouseListener = new MouseListener() {
            public void mouseClicked(MouseEvent aE) {
                if (aE.getClickCount() > 1) {
                    JTable lTable = (JTable) aE.getComponent();
                    Point lLocation = aE.getPoint();

                    int rowIndex = lTable.rowAtPoint(lLocation);
                    int columnIndex = lTable.columnAtPoint(lLocation);


                    ULPattern pattern = patterns.get(rowIndex);

                    /*
                    if (columnIndex == PatternTableModel.Columns.View) {





                        String link = "http://umllint.net/app/pattern/view/"+pattern.getId();
                        try {
                            Desktop.getDesktop().browse(new URI(link));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        //open link in browser
                    }
                    */



                    if (columnIndex == PatternTableModel.Columns.View) {

                        ApplicationManager.instance().getViewManager().showDialog(new PatternDetailDialog(pattern));
                    }

                }

            }

            public void mouseEntered(MouseEvent aE) {
            }

            public void mouseExited(MouseEvent aE) {
            }

            public void mousePressed(MouseEvent aE) {
            }

            public void mouseReleased(MouseEvent aE) {
            }
        };

        tableTable.addMouseListener(patternListTableMouseListener);


    }

	public boolean canClosed() {
		return true;
	}

	public Component getComponent() {
		return _component;
	}

	public void prepare(IDialog aDialog) {
		_dialog = aDialog;
		
		aDialog.setSize(500, 450);
		aDialog.setTitle("List Patterns");
		aDialog.setModal(false);
	}

	public void shown() {
	}


    private static class PatternCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable aTable, Object aValue, boolean aIsSelected, boolean aHasFocus, int aRow, int aColumn) {
            Component lComponent = super.getTableCellRendererComponent(aTable, aValue, aIsSelected, aHasFocus, aRow, aColumn);

            if (lComponent instanceof JLabel && aValue instanceof String) {
                JLabel lLabel = (JLabel) lComponent;
                lLabel.setText((String) aValue);
                //lLabel.setIcon(lIcon);
            }

            return lComponent;
        }
    }
	

    /*
	private static class ModelTypeCellRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable aTable, Object aValue, boolean aIsSelected, boolean aHasFocus, int aRow, int aColumn) {
			Component lComponent = super.getTableCellRendererComponent(aTable, aValue, aIsSelected, aHasFocus, aRow, aColumn);
			
			if (lComponent instanceof JLabel && aValue instanceof String) {
				JLabel lLabel = (JLabel) lComponent;
				String lModelType = (String) aValue;
				
				ViewManager lViewManager = ApplicationManager.instance().getViewManager();
				String lName = lViewManager.getDisplayModelType(lModelType);
				Icon lIcon = lViewManager.getIconByModelType(lModelType);
				
				lLabel.setText(lName);
				lLabel.setIcon(lIcon);
			}
			
			return lComponent;
		}
	}

	private static class ModelElementCellRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable aTable, Object aValue, boolean aIsSelected, boolean aHasFocus, int aRow, int aColumn) {
			Component lComponent = super.getTableCellRendererComponent(aTable, aValue, aIsSelected, aHasFocus, aRow, aColumn);
			
			if (lComponent instanceof JLabel && aValue instanceof IModelElement) {
				JLabel lLabel = (JLabel) lComponent;
				IModelElement lModelElement = (IModelElement) aValue;
				
				ViewManager lViewManager = ApplicationManager.instance().getViewManager();
				String lName = lModelElement.getNickname();
				Icon lIcon = lViewManager.getIconByModelType(lModelElement.getModelType());
				
				lLabel.setText(lName);
				lLabel.setIcon(lIcon);
			}
			
			return lComponent;
		}
	}
	*/

}
