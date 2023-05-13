/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entity.Cours;
import entity.Formation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;
import static javax.swing.UIManager.getInt;
import pidevv.Datasource;
import pidevv.datas;


public class CoursService implements NewInterfacej<Cours>  {
      private Connection cnx;
    private Statement ste;
    private static CoursService instance;
   
    
    private PreparedStatement pst;
    
    private Connection connection;
    private ResultSet rs;
public CoursService() {
        connection=Datasource.getInstance().getConnection();
    }    

public void ajouterCoursPST(Cours c){
    
          
          
        String req="insert into coursss (nom,description,video,dateajoutt,formations_id) values (?,?,?,?,?)";
        try {
            pst=connection.prepareStatement(req);  
            pst.setString(1, c.getNom());
            pst.setString(2,c.getDescription());
            pst.setString(3,c.getVideo());
            pst.setInt(5,c.getIdFormation());
            
           // java.sql.Date sqlDate=new java.sql.Date(c.getDteAjout().getTime());
            
            pst.setDate(4, (Date) c.getDteAjout());
            
            
            //pst.setInt(4, c.getFormation().getId());
            
            
  //          System.out.println(id_formation);
            pst.executeUpdate();
           
        } catch (SQLException ex) {
            System.out.println("hne");
            Logger.getLogger(formationcrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}   

   

   public List <Cours> readAll(){
            String req="select* from coursss ";          
            List <Cours> list=new ArrayList<>();
         try {
             pst=connection.prepareStatement(req);
               
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Cours(rs.getInt("id"),rs.getString("nom"), rs.getString("description"),rs.getInt("formations_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }   
            return list;
    }
    public void supprimerCours(Cours c ){
            String req="delete from coursss where id=? ";
            System.out.println("aaa");
          
                try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, c.getId());
            pst.executeUpdate();    
                }
            catch (SQLException ex) {
                Logger.getLogger(formationcrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
   /* @Override
     public List<Cours> afficherr(){
        List<Cours> personnes = new ArrayList<>();
        String requete = "SELECT * FROM `coursss`";
        
        try {
             ste = cnx.createStatement();
            ResultSet rs =  ste.executeQuery(requete);
             
             
          
           
           // pst = connection.createStatement();
         //   pst =  connection.createStatement();
            //ResultSet rs = pst.executeQuery(requete);
            
            while(rs.next()){
//                Personne p = new Personne();
//                p.setId(rs.getInt("id"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
//                personnes.add(p);                
//                personnes.add(new Cours(rs.getInt("id"), rs.getString("nom"), rs.getString("description")));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return personnes;
    }*/

  

    
    public void modifierFormationPST(Cours f) throws SQLException{
        String req="update coursss set nom=? ,description =?  ,video=?  where nom=? ";
        try {
            pst=connection.prepareStatement(req);
            
            pst.setString(1, f.getNom());
           
            pst.setString(2,f.getDescription());
        
            
            pst.setString(3,f.getVideo());
            pst.setString(4,f.getNom());
            
            
           // java.sql.Date sqlDate=new java.sql.Date(f.getDteAjout().getTime());
            
            //pst.setDate(4,sqlDate);
            
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
            pst=connection.prepareStatement(req);
        }
        
    }
     @Override
    public List<Cours> afficher(){
        List<Cours> personnes = new ArrayList<>();
        String requete = "SELECT coursss.*, formmattion.* FROM coursss  left join formmattion  ON formmattion.id=coursss.formations_id";
        Cours c= new Cours();
        try {
            //ste = cnx.createStatement();
              Statement stm = connection.createStatement();
            ResultSet rs =  stm.executeQuery(requete);
            
            while(rs.next()){
//                Personne p = new Personne();
//                p.setId(rs.getInt("id"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
//                personnes.add(p);      
                Formation f = new Formation(rs.getInt(7),rs.getString(8),rs.getString(9));
                personnes.add(new Cours(rs.getInt("id"), rs.getString("nom"), rs.getString(3), rs.getString(4), f));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return personnes;
    }
     public List <Cours> search(String title){
            String req="select* from coursss where nom LIKE '%"+title+"%'" ;
            List <Cours> list=new ArrayList<>();
            
       try {
            pst=connection.prepareStatement(req);
           //   pst.setString(1,title);
            rs=pst.executeQuery();
            
            while(rs.next()){
                list.add(new Cours(rs.getInt("id"),rs.getString("nom"),rs.getString("description"),rs.getString("video")));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }   
            return list;
        }
     public List<datas> getstat(){
         
        List<datas> Form = new ArrayList<>();
        float moy=0;
        double k=0;
       try { 
        Statement stm = connection.createStatement();
        ResultSet result;
          
              result = stm.executeQuery("select * from formmattion ");
          
        
        while(result.next()){
       
            Form.add(new datas(0,  result.getInt(1),result.getString(2),0));
         
        }
        for(int i=0;i<Form.size();i++)
        {
            int x=0;
            k=0;
        ResultSet result1 =  stm.executeQuery("select * from coursss where formations_id= "+Form.get(i).getId());
        while(result1.next()){
            x++;
            Form.get(i).setNbre(x);
            k=k+result1.getDouble(6);
           
            moy=(float) (k/x);
            Form.get(i).setRate(moy);
            
            
            
            
           
        }
        
        }
        return Form; 
        } catch (SQLException ex) {
    
           System.out.println(ex.getMessage());  
            
          }
        return Form;
    
    
    
    } 
     public String countProduct() {

        String req = "SELECT COUNT(*) FROM coursss";
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(req);
            pst.executeQuery(req);
            ResultSet rs = pst.getResultSet();
            rs.next();
            return ("  " + rs.getInt("count(*)") + " coursss");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }}
     
     public List<PieChart.Data> stat() {
        
                String req="SELECT formmattion.nom,COUNT(formmattion.id) as nbr FROM formmattion left join coursss on formmattion.id=coursss.formations_id group by formmattion.id";
                    List<PieChart.Data> list=new ArrayList<>();
                 //   Restau r = new Restau();
                   // int a =r.getcat().getId();
                  //  CatService ca = new CatService();
                   // Categorie m =new Categorie();
                    
        try {
             Statement stm = connection.createStatement();
             ResultSet result;
            result= stm.executeQuery(req);
            while(result.next()){
             list.add(new PieChart.Data(result.getString("nom"), result.getInt("nbr")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    } 
     
     
     
     public List getstatt() throws SQLException{
        List<datas> Form = new ArrayList<>();
        float moy=0;
        double k=0;
        Statement stm = connection.createStatement();
        
        ResultSet result =  stm.executeQuery("select * from formmattion ");
        while(result.next()){
       
            Form.add(new datas(0,  result.getInt(1),result.getString(2),0));
         
        }
        for(int i=0;i<Form.size();i++)
        {
            int x=0;
            k=0;
        ResultSet result1 =  stm.executeQuery("select * from coursss where formations_id= "+Form.get(i).getId());
        while(result1.next()){
            x++;
            Form.get(i).setNbre(x);
            k=k+result1.getDouble(6);
           
            moy=(float) (k/x);
            Form.get(i).setRate(moy);
            
            
            
            
           
        }
        
        }
        
        return Form;
    
    
    
    }
      public static CoursService getInstance() {
        if (instance == null) {
            instance = new CoursService();
        }
        return instance;
    }
      public Cours getCarById(int id) {
        String query = "SELECT * FROM coursss WHERE id ='" + id + "'";

        try {
            rs = ste.executeQuery(query);
            while (rs.next()) {
                Cours car = new Cours(rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
                return car;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
      public void Delete_Car(String carNumber) {
        try {
            String query = "DELETE FROM coursss WHERE nom ='" + carNumber + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("Car has been deleted");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
    
       
}

