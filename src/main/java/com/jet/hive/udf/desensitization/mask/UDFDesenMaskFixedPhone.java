package com.jet.hive.udf.desensitization.mask;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import com.jet.hive.udf.desensitization.ConstantDesensitization;
import com.jet.utils.desensitization.DesenMaskUtils;

@Description(name = "DesenMaskFixedPhone", value = "_FUNC_(input [,mask] ) - 固定电话号码后4位,其他脱敏成掩码\n" + "参数1：输入的银行卡号"
		+ "参数2:可选参数,自定义的掩码字符,默认值为*", extended = "Example:\n > select _FUNC_('01078903456','#')  \n "
				+ ",_FUNC_('051276890008')  \n " + "结果: 0107890####,05127689**** \n ")
public class UDFDesenMaskFixedPhone extends UDF {
	private Text result = new Text();

	public Text evaluate(Text input, Text mask) {
		if (input == null) {
			return null;
		}
		String value = DesenMaskUtils.maskFixedPhone(input.toString(), mask.toString());
		result.set(value);
		return result;
	}

	public Text evaluate(Text input) {
		if (input == null) {
			return null;
		}
		String value = DesenMaskUtils.maskFixedPhone(input.toString(), ConstantDesensitization.DEN_STR);
		result.set(value);
		return result;
	}

}
