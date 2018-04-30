package org.inzy.framework.codegenerate.generate.onetomany;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inzy.framework.codegenerate.database.InzyReadTable;
import org.inzy.framework.codegenerate.generate.CgformCodeGenerate;
import org.inzy.framework.codegenerate.generate.ICallBack;
import org.inzy.framework.codegenerate.pojo.CreateFileProperty;
import org.inzy.framework.codegenerate.pojo.onetomany.CodeParamEntity;
import org.inzy.framework.codegenerate.pojo.onetomany.SubTableEntity;
import org.inzy.framework.codegenerate.util.CodeDateUtils;
import org.inzy.framework.codegenerate.util.CodeResourceUtil;
import org.inzy.framework.codegenerate.util.NonceUtils;
import org.inzy.framework.codegenerate.util.def.FtlDef;
import org.inzy.framework.web.cgform.entity.config.CgFormFieldEntity;
import org.inzy.framework.web.cgform.entity.generate.GenerateEntity;

import freemarker.template.TemplateException;

public class CgformCodeGenerateOneToMany implements ICallBack {
	private static final Log log = LogFactory
			.getLog(CgformCodeGenerateOneToMany.class);

	private String entityPackage = "test";
	private String entityName = "Person";
	private String tableName = "person";
	private String ftlDescription = "用户";
	private String primaryKeyPolicy = "default";
	private String sequenceCode = "";

	private static String ftl_mode;

	public static String FTL_MODE_A = "A";
	public static String FTL_MODE_B = "B";

	private static List<SubTableEntity> subTabParam = new ArrayList();
	private static CreateFileProperty createFileProperty = new CreateFileProperty();
	public static int FIELD_ROW_NUM = 4;

	private List<SubTableEntity> subTabFtl = new ArrayList();

	static {
		createFileProperty.setActionFlag(true);
		createFileProperty.setServiceIFlag(true);
		createFileProperty.setJspFlag(true);
		createFileProperty.setServiceImplFlag(true);
		createFileProperty.setPageFlag(true);
		createFileProperty.setEntityFlag(true);
	}

	private CodeParamEntity codeParamEntityIn;

	private GenerateEntity mainG;

	private Map<String, GenerateEntity> subsG;

	private List<SubTableEntity> subTabParamIn;

	public CgformCodeGenerateOneToMany() {
	}

	public CgformCodeGenerateOneToMany(List<SubTableEntity> subTabParamIn,
			CodeParamEntity codeParamEntityIn, GenerateEntity mainG,
			Map<String, GenerateEntity> subsG) {
		this.entityName = codeParamEntityIn.getEntityName();
		this.entityPackage = codeParamEntityIn.getEntityPackage();
		this.tableName = codeParamEntityIn.getTableName();
		this.ftlDescription = codeParamEntityIn.getFtlDescription();
		subTabParam = codeParamEntityIn.getSubTabParam();
		ftl_mode = codeParamEntityIn.getFtl_mode();
		this.primaryKeyPolicy = "default";
		this.sequenceCode = codeParamEntityIn.getSequenceCode();
		this.subTabParamIn = subTabParamIn;
		this.mainG = mainG;
		this.subsG = subsG;
		this.codeParamEntityIn = codeParamEntityIn;
	}

	public Map<String, Object> execute() {
		Map<String, Object> data = new HashMap();

		data.put("bussiPackage", CodeResourceUtil.bussiPackage);

		data.put("entityPackage", this.entityPackage);

		data.put("entityName", this.entityName);

		data.put("tableName", this.tableName);

		data.put("ftl_description", this.ftlDescription);

		data.put("inzy_table_id", CodeResourceUtil.INZY_GENERATE_TABLE_ID);

		data.put(FtlDef.INZY_PRIMARY_KEY_POLICY,
				StringUtils.lowerCase(this.primaryKeyPolicy));
		data.put(FtlDef.INZY_SEQUENCE_CODE, this.sequenceCode);
		data.put("ftl_create_time", CodeDateUtils.dateToString(new Date()));

		data.put(
				FtlDef.FIELD_REQUIRED_NAME,
				Integer.valueOf(StringUtils
						.isNotEmpty(CodeResourceUtil.INZY_UI_FIELD_REQUIRED_NUM) ? Integer
						.parseInt(CodeResourceUtil.INZY_UI_FIELD_REQUIRED_NUM)
						: -1));

		data.put(
				FtlDef.SEARCH_FIELD_NUM,
				Integer.valueOf(StringUtils
						.isNotEmpty(CodeResourceUtil.INZY_UI_FIELD_SEARCH_NUM) ? Integer
						.parseInt(CodeResourceUtil.INZY_UI_FIELD_SEARCH_NUM)
						: -1));

		data.put(FtlDef.FIELD_ROW_NAME, Integer.valueOf(FIELD_ROW_NUM));

		try {
			Map<String, String> fieldMeta = new HashMap();
			List<CgFormFieldEntity> columns = this.mainG.deepCopy()
					.getCgFormHead().getColumns();
			if("default".equalsIgnoreCase(primaryKeyPolicy)) {
				for ( CgFormFieldEntity c : columns) {
					if (c.getFieldName().toLowerCase().equals(CodeResourceUtil.INZY_GENERATE_TABLE_ID.toLowerCase())
						|| CodeResourceUtil.isUiFilterFields(c.getFieldName())) {
						columns.remove(c);
					}
				}
			}
			String type;
			for (CgFormFieldEntity cf : columns) {
				type = cf.getType();
				if ("string".equalsIgnoreCase(type)) {
					cf.setType("java.lang.String");
				} else if ("Date".equalsIgnoreCase(type)) {
					cf.setType("java.util.Date");
				} else if ("double".equalsIgnoreCase(type)) {
					cf.setType("java.lang.Double");
				} else if ("int".equalsIgnoreCase(type)) {
					cf.setType("java.lang.Integer");
				} else if ("BigDecimal".equalsIgnoreCase(type)) {
					cf.setType("java.math.BigDecimal");
				} else if ("Text".equalsIgnoreCase(type)) {
					cf.setType("javax.xml.soap.Text");
				} else if ("Blob".equalsIgnoreCase(type)) {
					cf.setType("java.sql.Blob");
				}
				String fieldName = cf.getFieldName();
				String fieldNameV = InzyReadTable.formatField(fieldName);
				cf.setFieldName(fieldNameV);
				fieldMeta.put(fieldNameV, fieldName.toUpperCase());
			}
			List<CgFormFieldEntity> pageColumns = new ArrayList();
			for (CgFormFieldEntity cf : columns) {
				if ((StringUtils.isNotEmpty(cf.getIsShow()))
						&& ("Y".equalsIgnoreCase(cf.getIsShow()))) {
					pageColumns.add(cf);
				}
			}
			String[] subtables = this.mainG.getCgFormHead().getSubTableStr()
					.split(",");

			data.put("cgformConfig", this.mainG);
			data.put("fieldMeta", fieldMeta);
			data.put("columns", columns);
			data.put("pageColumns", pageColumns);
			data.put("buttons",
					this.mainG.getButtons() == null ? new ArrayList(0)
							: this.mainG.getButtons());
			data.put("buttonSqlMap",
					this.mainG.getButtonSqlMap() == null ? new HashMap(0)
							: this.mainG.getButtonSqlMap());
			data.put("subtables", subtables);
			data.put("subTab", this.subTabParamIn);

			Map<String, List<CgFormFieldEntity>> subColumnsMap = new HashMap(0);
			Map<String, List<CgFormFieldEntity>> subPageColumnsMap = new HashMap(
					0);
			Map<String, String> subFieldMeta = new HashMap(0);
			Map<String, String> subFieldMeta1 = new HashMap(0);
			for (String key : this.subsG.keySet()) {
				GenerateEntity subG = (GenerateEntity) this.subsG.get(key);
				List<CgFormFieldEntity> subColumns = subG.deepCopy()
						.getCgFormHead().getColumns();
				List<CgFormFieldEntity> subPageColumns = new ArrayList();
				for (CgFormFieldEntity cf : subColumns) {
					type = cf.getType();
					if ("string".equalsIgnoreCase(type)) {
						cf.setType("java.lang.String");
					} else if ("Date".equalsIgnoreCase(type)) {
						cf.setType("java.util.Date");
					} else if ("double".equalsIgnoreCase(type)) {
						cf.setType("java.lang.Double");
					} else if ("int".equalsIgnoreCase(type)) {
						cf.setType("java.lang.Integer");
					} else if ("BigDecimal".equalsIgnoreCase(type)) {
						cf.setType("java.math.BigDecimal");
					} else if ("Text".equalsIgnoreCase(type)) {
						cf.setType("javax.xml.soap.Text");
					} else if ("Blob".equalsIgnoreCase(type)) {
						cf.setType("java.sql.Blob");
					}
					String fieldName = cf.getFieldName();
					String fieldNameV = InzyReadTable.formatField(fieldName);
					cf.setFieldName(fieldNameV);
					subFieldMeta.put(fieldNameV, fieldName.toUpperCase());
					subFieldMeta1.put(fieldName.toUpperCase(), fieldNameV);
					if ((StringUtils.isNotEmpty(cf.getIsShow()))
							&& ("Y".equalsIgnoreCase(cf.getIsShow()))) {
						subPageColumns.add(cf);
					}
					String mtable = cf.getMainTable();
					String mfiled = cf.getMainField();
					if ((mtable != null)
							&& (mtable.equalsIgnoreCase(this.mainG
									.getTableName()))) {
						data.put(key + "_fk", mfiled);
					}
					subColumnsMap.put(key, subColumns);
					subPageColumnsMap.put(key, subPageColumns);
				}
				data.put("subColumnsMap", subColumnsMap);
				data.put("subPageColumnsMap", subPageColumnsMap);
				data.put("subFieldMeta", subFieldMeta);
				data.put("subFieldMeta1", subFieldMeta1);
				data.put("packageStyle", this.mainG.getPackageStyle());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long serialVersionUID = NonceUtils.randomLong()
				+ NonceUtils.currentMills();
		data.put("serialVersionUID", String.valueOf(serialVersionUID));
		return data;
	}

	public void generateToFile() throws TemplateException, IOException {
		CgformCodeFactoryOneToMany codeFactoryOneToMany = new CgformCodeFactoryOneToMany();
		codeFactoryOneToMany.setProjectPath(this.mainG.getProjectPath());
		codeFactoryOneToMany.setPackageStyle(this.mainG.getPackageStyle());
		codeFactoryOneToMany.setCallBack(new CgformCodeGenerateOneToMany(
				this.subTabParamIn, this.codeParamEntityIn, this.mainG,
				this.subsG));

		if (createFileProperty.isJspFlag()) {
			codeFactoryOneToMany.invoke("onetomany/cgform_jspListTemplate.ftl",
					"jspList");
			codeFactoryOneToMany.invoke("onetomany/cgform_jspTemplate_add.ftl",
					"jsp_add");
			codeFactoryOneToMany.invoke(
					"onetomany/cgform_jspTemplate_update.ftl", "jsp_update");
			codeFactoryOneToMany.invoke(
					"onetomany/cgform_jsEnhanceTemplate.ftl", "js");
			codeFactoryOneToMany.invoke(
					"onetomany/cgform_jsListEnhanceTemplate.ftl", "jsList");
		}
		if (createFileProperty.isServiceImplFlag()) {
			codeFactoryOneToMany.invoke(
					"onetomany/cgform_serviceImplTemplate.ftl", "serviceImpl");
		}
		if (createFileProperty.isServiceIFlag()) {
			codeFactoryOneToMany.invoke(
					"onetomany/cgform_serviceITemplate.ftl", "service");
		}
		if (createFileProperty.isActionFlag()) {
			codeFactoryOneToMany.invoke(
					"onetomany/cgform_controllerTemplate.ftl", "controller");
		}
		if (createFileProperty.isEntityFlag()) {
			codeFactoryOneToMany.invoke("onetomany/cgform_entityTemplate.ftl",
					"entity");
		}
		if (createFileProperty.isPageFlag()) {
			codeFactoryOneToMany.invoke("onetomany/cgform_pageTemplate.ftl",
					"page");
		}
	}

	public static void oneToManyCreate(List<SubTableEntity> subTabParamIn,
			CodeParamEntity codeParamEntityIn, GenerateEntity mainG,
			Map<String, GenerateEntity> subsG) throws TemplateException,
			IOException {
		log.info("----inzy----Code-----Generation-----[一对多数据模型："
				+ codeParamEntityIn.getTableName() + "]------- 生成中。。。");

		CreateFileProperty subFileProperty = new CreateFileProperty();
		subFileProperty.setActionFlag(false);
		subFileProperty.setServiceIFlag(false);
		subFileProperty.setJspFlag(true);
		subFileProperty.setServiceImplFlag(false);
		subFileProperty.setPageFlag(false);
		subFileProperty.setEntityFlag(true);
		subFileProperty.setJspMode("03");

		for (SubTableEntity sub : subTabParamIn) {
			String[] foreignKeys = sub.getForeignKeys();
			GenerateEntity subG = (GenerateEntity) subsG
					.get(sub.getTableName());
			new CgformCodeGenerate(sub, subG, subFileProperty, "uuid",
					foreignKeys).generateToFile();
		}

		new CgformCodeGenerateOneToMany(subTabParamIn, codeParamEntityIn,
				mainG, subsG).generateToFile();
		log.info("----inzy----Code----Generation------[一对多数据模型："
				+ codeParamEntityIn.getTableName() + "]------ 生成完成。。。");
	}
}