package com.trantrongnhan.Reponsitory.impl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.trantrantrongnhan.mapper.RowMapper;
import com.trantrongnhan.Reponsitory.GenericReponsitory;
import com.trantrongnhan.annotations.Column;
import com.trantrongnhan.annotations.Table;
import com.trantrongnhan.paging.PageRequest;
import com.trantrongnhan.sorting.Sorter;

public class AbstractReponsitory<T> implements GenericReponsitory<T> {
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public AbstractReponsitory() {
		Type t = this.getClass().getGenericSuperclass();
		ParameterizedType para = (ParameterizedType) t;
		clazz = (Class<T>) para.getActualTypeArguments()[0];

		// clazz=(Class<T>) t.getActualTypeArguments()[0];

	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String user = "root";
			String password = "123456789";
			String url = "jdbc:mysql://localhost:3306/estate4month2019";
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> findAll() {
		List<T> result = new ArrayList<T>();
		StringBuilder sql = new StringBuilder("select * from ");

		Table t = clazz.getAnnotation(Table.class);
		sql.append(t.name());
		Connection conn = null;
		PreparedStatement statement = null;

		ResultSet resultSet = null;
		conn = getConnection();
		if (conn != null) {
			try {
				statement = conn.prepareStatement(sql.toString());
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					result.add(new RowMapper<T>().mapRow(resultSet, clazz));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {

				try {
					if (resultSet != null) {
						resultSet.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return result;
	}

	@Override
	public Integer insert(T obj) {
		Integer result = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {

			StringBuilder sql = new StringBuilder("insert into ");
			Table t = clazz.getAnnotation(Table.class);
			sql.append(t.name() + "(");
			// Method[] methods = clazz.getDeclaredMethods();
			Method[] methods = clazz.getMethods();
			Object[] parameters = new Object[100000];
			int res = 0;
			for (int i = 0; i < methods.length; i++) {

				if ((methods[i].getName().startsWith("get")) && (methods[i].invoke(obj) != null)) {

					if (methods[i].isAnnotationPresent(Column.class)) {
						if (res > 0) {
							sql.append(",");
						}
						Column col = methods[i].getDeclaredAnnotation(Column.class);
						sql.append(col.name());
						res++;
						parameters[res] = methods[i].invoke(obj);
					}

				}

			}
			sql.append(") values(");
			for (int i = 1; i <= res; i++) {
				if (i > 1) {
					sql.append(",");
				}
				sql.append("?");
			}
			sql.append(")");
			System.out.println(sql.toString());
			conn = getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				for (int i = 1; i <= res; i++) {
					statement.setObject(i, parameters[i]);
				}
				if (statement.executeUpdate() > 0) {
					conn.commit();
					resultSet = statement.getGeneratedKeys();
					resultSet.next();
					result = resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return result;
	}

	@Override
	public void update(T obj) {
		StringBuilder sql = new StringBuilder("update ");
		Table t = clazz.getAnnotation(Table.class);
		sql.append(t.name() + " set ");
		Method[] methods = clazz.getMethods();
		int count = 0;
		Object[] parameters = new Object[100000];
		try {
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().startsWith("get")) {
					if (!methods[i].getName().equals("getId")) {
						if (methods[i].isAnnotationPresent(Column.class)) {
							Column col = methods[i].getAnnotation(Column.class);
							if (count > 0) {
								sql.append(",");

							}
							sql.append(col.name() + "=?");
							count++;
							parameters[count] = methods[i].invoke(obj);
						}
					}
				}
			}
			sql.append(" where id=?");
			count++;
			parameters[count] = clazz.getMethod("getId").invoke(obj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Connection conn = null;
		PreparedStatement statement = null;
		conn = getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				statement = conn.prepareStatement(sql.toString());
				for (int i = 1; i <= count; i++) {
					statement.setObject(i, parameters[i]);
				}
				if (statement != null) {
					if (statement.executeUpdate() > 0) {
						conn.commit();
						System.out.println(sql.toString());
					}
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
			} finally {
				try {
					if (statement != null)
						statement.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}

		}
	}

	@Override
	public void delete(T obj) {
		StringBuilder sql = new StringBuilder("delete from ");
		Table t = clazz.getAnnotation(Table.class);
		sql.append(t.name());
		sql.append(" where id=?");
		Connection conn = null;
		PreparedStatement statement = null;
		conn = getConnection();
		try {
			Object id = clazz.getMethod("getId").invoke(obj);
			if (conn != null) {
				conn.setAutoCommit(false);
				statement = conn.prepareStatement(sql.toString());
				if (statement != null) {
					statement.setObject(1, id);
					if (statement.executeUpdate() > 0) {
						conn.commit();
						System.out.println(sql.toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public T findById(Integer id) {
		StringBuilder sql=new StringBuilder("select * from ");
		Table t=clazz.getAnnotation(Table.class);
		sql.append(t.name());
		sql.append(" where id=?");
		//System.out.println(sql.toString());
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		conn=getConnection();
		try {
			T result=clazz.newInstance();
			if(conn!=null) {
				
				statement=conn.prepareStatement(sql.toString());
				if(statement!=null) {
					statement.setObject(1,id);
					resultSet=statement.executeQuery();
					resultSet.next();
					System.out.println(resultSet.getString("name"));
					RowMapper<T> tMapper=new RowMapper<T>();
					return tMapper.mapRow(resultSet, clazz);
				}
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(resultSet!=null)
					resultSet.close();
				if(statement!=null)
					statement.close();
				if(conn!=null)
					conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@Override
	public List<T> search(T obj,PageRequest pageRequest,Sorter sorter) {
		
		List<T> result=new ArrayList();
		StringBuilder sql=new StringBuilder("select * from ");
		Table table=clazz.getAnnotation(Table.class);
		sql.append(table.name());
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		conn=getConnection();
		try {
			Method[] methods=clazz.getMethods();
			Object[] parameters=new Object[100000];
			int count=0;
			for(int i=0;i<methods.length;i++) {
				if((methods[i].getName().startsWith("get"))) {
					if(methods[i].isAnnotationPresent(Column.class)) {
						if(methods[i].invoke(obj)!=null) {
							
							if(count>0) {
								sql.append(" and ");
							}
							else {
								sql.append(" where ");
							}
							count++;
							sql.append(methods[i].getAnnotation(Column.class).name());
							if(methods[i].getReturnType()==String.class) {
								sql.append(" like ?");
								parameters[count]="%"+methods[i].invoke(obj).toString()+"%";
						
							}
							else {
								sql.append(" = ?");
								parameters[count]=methods[i].invoke(obj);
							}
						}
					}
				}
					
			}
			if(sorter!=null) {
				if((sorter.getOrderBy()!=null)&&(sorter.getSortBy()!=null)) {
					sql.append(" order by "+sorter.getOrderBy()+" "+sorter.getSortBy());
					
				}
			}
			if(pageRequest!=null) {
				if((pageRequest.getOffset()!=null)&&(pageRequest.getLimit()!=null)) {
					sql.append(" limit "+pageRequest.getOffset()+","+pageRequest.getLimit());
				}
			}
			System.out.println(sql.toString());
			if(conn!=null) {
				statement=conn.prepareStatement(sql.toString());
				if(statement!=null) {
					for(int i=1;i<=count;i++)
						statement.setObject(i, parameters[i]);
				}
				if(statement!=null) {
					resultSet=statement.executeQuery();
					RowMapper<T> rowMapper=new RowMapper<T>();
					while(resultSet.next()) {
						result.add(rowMapper.mapRow(resultSet, clazz));
					}
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(resultSet!=null)
					resultSet.close();
				if(statement!=null)
					statement.close();
				if(conn!=null)
					conn.close();
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		return result;
		
	}

}
