package com.jet.hive.udf.desensitization.mask;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

import com.jet.hive.udf.desensitization.ConstantDesensitization;
import com.jet.utils.desensitization.DesenMaskUtils;

@Description(name = "DesenMaskPositionExclude", value = "_FUNC_(input,numsStr [,mask]) - 将字符串中除了某些位之外的字符脱敏成掩码\n"
		+ "参数1:被脱敏的字符串" + "参数2:不脱敏的字符位置:'1~5,8,-2'表示第1~5位，第8位和右起第2位"
		+ "参数3:可选参数,自定义的掩码字符,默认值为*", extended = "Example:\n > select _FUNC_('abc1234def','1~3,7,-1','#')  \n "
				+ ",_FUNC_('abc1234def','1~-4,-1')  \n " + "结果:abc###4##f,abc1234**f  \n ")
public class UDFDesenMaskPositionExclude extends UDF {

	private Text result = new Text();

	public Text evaluate(Text input, Text numsStr, Text mask) {
		if (input == null) {
			return null;
		}
		String value = DesenMaskUtils.maskExclude(input.toString(), numsStr.toString(), mask.toString());
		result.set(value);
		return result;
	}

	public Text evaluate(Text input, Text numsStr) {
		if (input == null) {
			return null;
		}
		String value = DesenMaskUtils.maskExclude(input.toString(), numsStr.toString(),
				ConstantDesensitization.DEN_STR);
		result.set(value);
		return result;
	}

}
