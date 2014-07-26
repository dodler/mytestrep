/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package haulmaunt.lyan.ui;

import haulmaunt.lyan.student.CustomStudent;
import haulmaunt.lyan.student.Faculty;
import haulmaunt.lyan.student.Group;
import haulmaunt.lyan.student.Student;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Артем
 */
public class CustomScreen extends JFrame{
    
    private JTable table; // таблица с группами
    
    private Faculty faculty; // факультет
    private ArrayList<Group> groups; // список групп 
    private ArrayList<Student> students; // список студентов
    
    private JButton addGroup; // кнопка добавления группы
    private JButton editGroup;// редактирование группы
    private JButton removeGroup;  // удаление группы
    
    private GroupTableModel groupTableModel;

    private StudentTableModel studentTableModel;
    
    private WindowListener myWindowListener = new WindowListener(){

        @Override
        public void windowOpened(WindowEvent e) {
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0); // TODO может понадобится какая то обработка
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
            
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            
        }

        @Override
        public void windowActivated(WindowEvent e) {
            
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            
        }
        
    };
    
    private JScrollPane scrollPane;
    
    public CustomScreen(){
        super("Приложение для работы со студентами");
        
        groups = new ArrayList<Group>();
        students = new ArrayList<Student>();
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(800, 600);
        
        addGroup = new JButton("Добавить группу");
        editGroup = new JButton("Редактировать группу");
        removeGroup = new JButton("Удалить группу");
        
        this.addWindowListener(myWindowListener);
        
        groupTableModel = new GroupTableModel(groups);
        studentTableModel = new StudentTableModel(students);
        
        table = new JTable(studentTableModel);
        scrollPane = new JScrollPane(table);
    }
    
    public void addStudent(String name, String sirName, String parentName, Group parentGroup, String faculty){
        students.add(new CustomStudent(name, sirName, parentName, parentGroup, faculty));
    }
    
    public void addStudent(Student student){
        students.add(student);
        groups.get(groups.indexOf(student.getParentGroup())).addStudents(student);
    }
    
    /**
     * добавляет группы в список отображения
     * @param group - группа, которую нужно добавить
     */
    public void addGroup(Group group){
        groups.add(group);
        for(Student s:group.getStudents()){
            students.add(s);
        }
    }
   
    public void initUI(){
        this.setVisible(true);
        studentTableModel.setStudents(students);
        table.setModel(studentTableModel);
        
        scrollPane.setBounds(10, 10, 300, 600);
        
        addGroup.setBounds(10,400, addGroup.getWidth(), addGroup.getHeight());
        editGroup.setBounds(10 + addGroup.getWidth(), 400, editGroup.getWidth(), editGroup.getHeight());
        removeGroup.setBounds(20 + removeGroup.getWidth() + addGroup.getWidth(), 400, removeGroup.getWidth(), removeGroup.getHeight());
        
        add(addGroup);
        add(editGroup);
        add(removeGroup);
        
        add(scrollPane);
        repaint();
    }
    
}
