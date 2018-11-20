package com.codefans.practicetask.extract;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.util.JdbcConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ShengzhiCai
 * @date: 2018-11-20 11:19
 */
public class SqlExtractor extends AbstractExtractor {


    public static void main(String[] args) {
        SqlExtractor sqlExtractor = new SqlExtractor();
        sqlExtractor.taskStartup();
    }

    public void taskStartup() {

        String sql = extract();
        System.out.println("sql:");
        System.out.println(sql);

    }

    @Override
    public String extract() {

        try {

            Class.forName("org.gjt.mm.mysql.Driver");

            String userName = "username";
            String password = "password";
            String url = "jdbc:mysql://hostname:port/dbname?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&amp;useSSL=false";

            String newSql = "insert into ";

            String sql = "select c.bin_id, c.bank_code, c.card_type, c.card_name, c.bin_no, c.card_length, c.bank_no, c.extensions, c.enable_flag, c.memo, c.version, now() AS gmt_create, now() AS gmt_modify, b.bank_name " +
            "from t_card_bin c " +
                    "left join t_bank b ON c.bank_code = b.bank_no";
            String newTableName = "t_card_bin";

//            String sql = "select ic.bank_no, b.bank_name, 'UMPAY' AS 'channel_type' , '3497' AS 'merchant_id', 'FUND_IN' AS 'trade_type', 0 AS 'state', cl.once_limit, cl.day_limit, now() AS 'create_date', now() AS 'update_date' " +
//                    "from t_inpara_channel ic " +
//                    "left join t_bank b ON ic.bank_no = b.bank_no " +
//                    "left join " +
//                    "(select bank_code, once_limit, day_limit from t_channel_limit group by bank_code) cl ON cl.bank_code=ic.bank_no;";
//            String newTableName = "t_support_bankcard";

            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.println("columnCount=" + columnCount);

            List<String> columnNameList = new ArrayList<String>();
            StringBuffer newSqlBuff = new StringBuffer();
            newSqlBuff.append(newSql);
            newSqlBuff.append(newTableName);
            newSqlBuff.append(" (");

            String columnName = "";
            String columnLabel = "";
            int columnType = -1;
            String columnTypeName = "";
            for(int i = 1; i <= columnCount; i ++) {
                columnName = metaData.getColumnName(i);
                columnLabel = metaData.getColumnLabel(i);
                columnType = metaData.getColumnType(i);
                columnTypeName = metaData.getColumnTypeName(i);
                columnNameList.add(columnName);
//                System.out.println("columnName=" + columnName + ", columnLabel=" + columnLabel + ", columnType=" + columnType + ", columnTypeName=" + columnTypeName);

                if(i == 1) {
                    newSqlBuff.append(columnName);
                } else {
                    newSqlBuff.append(", ");
                    newSqlBuff.append(columnName);
                }

            }
            newSqlBuff.append(") values (");

            List<List<String>> dataList = new ArrayList<List<String>>();
            List<String> rowDataList = null;

            int columnIndex = -1;
            while(resultSet.next()) {
                columnIndex = 1;
                rowDataList = new ArrayList<String>(columnCount);
                for(int i = 0; i < columnCount; i ++) {
                    rowDataList.add(resultSet.getString(columnIndex++));
                }
                dataList.add(rowDataList);
            }

            System.out.println("dataList.size()=" + dataList.size());

            List<String> row = null;
            int rowTotal = dataList.size();
            int newSqlRowCount = rowTotal;
            String columnValue = null;
            for(int i = 0; i < newSqlRowCount; i ++) {
                row = dataList.get(i);
                for(int j = 0; j < row.size(); j ++) {
                    columnValue = row.get(j);
                    if(j == 0) {
                        if(columnValue == null) {
                            newSqlBuff.append(columnValue);
                        } else {
                            newSqlBuff.append("'").append(columnValue).append("'");
                        }
                    } else {
                        if (columnValue == null) {
                            if(j == row.size() - 1) {
                                newSqlBuff.append(", '北京市商业银行'");
                            } else {
                                newSqlBuff.append(", ").append(columnValue);
                            }
                        } else {
                            newSqlBuff.append(", '").append(columnValue).append("'");
                        }
                    }



                }
                if(i == newSqlRowCount - 1) {
                    newSqlBuff.append("); ");
                } else {
                    newSqlBuff.append("),(");
                }
            }
            System.out.println("newSQL:");
            //SQL校验并格式化
            String result = SQLUtils.format(newSqlBuff.toString(), JdbcConstants.MYSQL);
//            System.out.println(result);
            System.out.println(newSqlBuff.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error error) {
            error.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return null;
    }
}
