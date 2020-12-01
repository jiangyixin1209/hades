package top.jiangyixin.hades;

import lombok.Data;

/**
 * @author jiangyixin
 */
@Data
public class Query {
	private Long dbConfigId;
	private Long tableName;
	private Integer page;
	private Integer limit;
	private String tables;
}
