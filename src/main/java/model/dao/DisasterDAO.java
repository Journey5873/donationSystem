package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DisasterArticle;

public class DisasterDAO {
   // DisasterDao

   private JDBCUtil jdbcUtil = null;

   public DisasterDAO() {
      jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
   }

   public int create(DisasterArticle disaster) throws SQLException {
      try {
         String insert1 = "INSERT INTO DONATION_ARTICLE VALUES (seq_donation_article_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";

         Object[] param1 = new Object[] { disaster.getTitle(), disaster.getCategory(), disaster.getDeadline(),
               disaster.getBankName(), disaster.getAccHolder(), disaster.getAccNum(), disaster.getIdCheck(),
               disaster.getDueDate(), disaster.getUsePlan(), disaster.getOtherText(), disaster.getUpdateDate(),
               disaster.getReceiptCheck(), disaster.getTotalAmount(), disaster.getUserId() };

         System.out.println("DisasterDao" + disaster);

         jdbcUtil.setSqlAndParameters(insert1, param1);

         String key[] = { "article_id" };

         jdbcUtil.executeUpdate(key);

         ResultSet rs = jdbcUtil.getGeneratedKeys();

         int generatedKey = 0;

         if (rs.next()) {
            generatedKey = rs.getInt(1);

            String insert2 = "INSERT INTO DISASTER_ARTICLE VALUES (?, ?, ?, ?, ?, ?)";
            Object[] param2 = new Object[] { generatedKey, disaster.getType(), disaster.getName(),
                  disaster.getArea(), disaster.getDamageAmount(), disaster.getSituation() };

            jdbcUtil.setSqlAndParameters(insert2, param2);

            jdbcUtil.executeUpdate();
         }

         return generatedKey;

      } catch (Exception e) {
         // TODO: handle exception
         jdbcUtil.rollback();
         e.printStackTrace();
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();
      }

      return -1;
   }

   public DisasterArticle findDisasterArticle(int article_id) throws SQLException {
      String sql = "SELECT title, cateory, deadline, bank_name, acc_holder, acc_num, due_date, use_plan, other_text, TO_CHAR(CREATE_DATE,'YYYY-MM-DD') \"create_date\", TO_CHAR(UPDATE_DATE, 'YYYY-MM-DD') \"update_date\", receipt_check, user_id, total_amount, type, name, area, damage_amount, situation "
            + "FROM disaster_article a JOIN donation_article d ON a.article_id = d.article_id "
            + "WHERE a.article_id=? ";

      jdbcUtil.setSqlAndParameters(sql, new Object[] { article_id });

      try {
         ResultSet rs = jdbcUtil.executeQuery();

         if (rs.next()) {
            DisasterArticle article = new DisasterArticle(article_id, rs.getString("title"),
                  rs.getString("category"), rs.getString("deadline"), rs.getString("bank_name"),
                  rs.getString("acc_holder"), rs.getString("acc_num"), rs.getString("due_date"),
                  rs.getString("use_plan"), rs.getString("other_text"), rs.getString("create_date"),
                  rs.getString("update_date"), rs.getString("receipt_check"), rs.getString("use_id"),
                  rs.getInt("total_amount"), rs.getString("type"), rs.getString("name"), rs.getString("area"),
					rs.getInt("damage_amount"), rs.getString("situation"));

            return article;
         }
      } catch (Exception e) {
         // TODO: handle exception
         jdbcUtil.rollback();
         e.printStackTrace();
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();
      }

      return null;
   }

   public int update(DisasterArticle article) throws SQLException {
      try {
         String update1 = "UPDATE DONATION_ARTICLE "
               + "SET title=?, due_date=?, use_plan=?, other_text=?, update_date=SYSDATE, receipt_check=? "
               + "WHERE article_id=? ";

         Object[] param1 = new Object[] { article.getTitle(), article.getDueDate(), article.getUsePlan(),
               article.getOtherText(), article.getReceiptCheck(), article.getArticleId() };

         jdbcUtil.setSqlAndParameters(update1, param1);
         jdbcUtil.executeUpdate();

         String update2 = "UPDATE DISASTER_ARTICLE "
               + "SET type = ?, name = ?, area = ?, damage_amount = ?, situation = ? "
               + "where article_id=? ";

         Object[] param2 = new Object[] { article.getType(), article.getName(), article.getArea(),
               article.getDamageAmount(), article.getSituation() };

         jdbcUtil.setSqlAndParameters(update2, param2);
         jdbcUtil.executeUpdate();

         return 1;
      } catch (Exception e) {
         // TODO: handle exception
         jdbcUtil.rollback();
         e.printStackTrace();
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();
      }

      return 0;
   }

//   아직 미완성
   public int delete(DisasterArticle disaster) {
      // TODO Auto-generated method stub
      try {
         String sql = "DELETE ";
         
      } catch (Exception e) {
         // TODO: handle exception
         jdbcUtil.rollback();
         e.printStackTrace();
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();
      }
      
      return 0;
   }
}