package org.purc.purcforms.util;


/**
 * 
 * @author daniel
 *
 */
public class XFormsUtil {

    public static String getExampleXform1() {
        return "<xf:xforms xmlns:xf=\"http://www.w3.org/2002/xforms\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
            +"<xf:model>"
            +"    <xf:instance id=\"test1\">"
            +"      <test1 name=\"test form 1\" id=\"9\">"
            +"        <question1/>"
            +"        <question2/>"
            +"      </test1>"
            +"    </xf:instance>"
            +"    <xf:bind id=\"question1\" nodeset=\"/test1/question1\" type=\"xsd:string\"/>"
            +"    <xf:bind id=\"question2\" nodeset=\"/test1/question2\" type=\"xsd:string\"/>"
            +"  </xf:model>"
            +"  <xf:group id=\"1\">"
            +"    <xf:label>Page1</xf:label>"
            +"    <xf:input bind=\"question1\">"
            +"      <xf:label>Question1</xf:label>"
            +"    </xf:input>"
            +"    <xf:select1 bind=\"question2\">"
            +"      <xf:label>Question2</xf:label>"
            +"      <xf:item id=\"option1\">"
            +"        <xf:label>Option1</xf:label>"
            +"        <xf:value>option1</xf:value>"
            +"      </xf:item>"
            +"      <xf:item id=\"option2\">"
            +"        <xf:label>Option2</xf:label>"
            +"        <xf:value>option2</xf:value>"
            +"      </xf:item>"
            +"    </xf:select1>"
            +"  </xf:group>"
            +"</xf:xforms>";
    }
    
    public static String getExampleXform2() {
        return "<xf:xforms xmlns:xf=\"http://www.w3.org/2002/xforms\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
            +"  <xf:model>"
            +"    <xf:instance id=\"test2\">"
            +"      <test1 name=\"test form 2\" id=\"9\">"
            +"        <question3/>"
            +"        <question4/>"
            +"        <question5/>"
            +"      </test1>"
            +"    </xf:instance>"
            +"    <xf:bind id=\"question3\" nodeset=\"/test2/question3\" type=\"xsd:int\"/>"
            +"    <xf:bind id=\"question4\" nodeset=\"/test2/question4\" type=\"xsd:boolean\"/>"
            +"    <xf:bind id=\"question5\" nodeset=\"/test2/question5\" type=\"xsd:date\"/>"
            +"  </xf:model>"
            +"  <xf:group id=\"1\">"
            +"    <xf:label>Page1</xf:label>"
            +"    <xf:input bind=\"question3\">"
            +"      <xf:label>Question3</xf:label>"
            +"    </xf:input>"
            +"    <xf:input bind=\"question4\">"
            +"      <xf:label>Question4</xf:label>"
            +"    </xf:input>"
            +"    <xf:input bind=\"question5\">"
            +"      <xf:label>Question5</xf:label>"
            +"    </xf:input>"
            +"  </xf:group>"
            +"</xf:xforms>";
    }
    
    public static String getSampleForm() {
        return
        "<xf:xforms xmlns:xf=\"http://www.w3.org/2002/xforms\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
        +"<xf:model>"
          +"<xf:instance id=\"patientreg\">"
            +"<patientreg name=\"Patient Registration\" id=\"12\" description-template=\"${/patientreg/lastname}$ in ${/patientreg/continent}$\">"
              +"<patientid/>"
              +"<title/>"
              +"<firstname/>"
              +"<lastname/>"
              +"<sex/>"
              +"<birthdate/>"
              +"<weight/>"
              +"<height/>"
              +"<pregnant/>"
              +"<arvs/>"
              +"<picture/>"
              +"<coughsound/>"
              +"<recordvideo/>"
              +"<continent/>"
              +"<country/>"
              +"<district/>"
              +"<village/>"
              +"<nokids>0</nokids>"
              +"<kids>"
                +"<kid>"
                  +"<kidname/>"
                  +"<kidsex/>"
                  +"<kidage/>"
                +"</kid>"
              +"</kids>"
              +"<starttime/>"
              +"<endtime/>"
            +"</patientreg>"
          +"</xf:instance>"
          +"<xf:instance id=\"country\">"
            +"<dynamiclist>"
              +"<item id=\"uganda\" parent=\"africa\">"
                +"<label>Uganda</label>"
                +"<value>uganda</value>"
              +"</item>"
              +"<item id=\"kenya\" parent=\"africa\">"
                +"<label>Kenya</label>"
                +"<value>kenya</value>"
              +"</item>"
              +"<item id=\"tanzania\" parent=\"africa\">"
                +"<label>Tanzania</label>"
                +"<value>tanzania</value>"
              +"</item>"
              +"<item id=\"rwanda\" parent=\"africa\">"
                +"<label>Rwanda</label>"
                +"<value>rwanda</value>"
              +"</item>"
              +"<item id=\"malaysia\" parent=\"asia\">"
                +"<label>Malaysia</label>"
                +"<value>malaysia</value>"
              +"</item>"
              +"<item id=\"india\" parent=\"asia\">"
                +"<label>India</label>"
                +"<value>india</value>"
              +"</item>"
              +"<item id=\"china\" parent=\"asia\">"
                +"<label>China</label>"
                +"<value>china</value>"
              +"</item>"
              +"<item id=\"brazil\" parent=\"southamerica\">"
                +"<label>Brazil</label>"
                +"<value>brazil</value>"
              +"</item>"
              +"<item id=\"argentina\" parent=\"southamerica\">"
                +"<label>Argentina</label>"
                +"<value>argentina</value>"
              +"</item>"
              +"<item id=\"canada\" parent=\"northamerica\">"
                +"<label>Canada</label>"
                +"<value>canada</value>"
              +"</item>"
              +"<item id=\"usa\" parent=\"northamerica\">"
                +"<label>USA</label>"
                +"<value>usa</value>"
              +"</item>"
              +"<item id=\"singapore\" parent=\"asia\">"
                +"<label>Singapore</label>"
                +"<value>singapore</value>"
              +"</item>"
              +"<item id=\"newzealand\" parent=\"australia\">"
                +"<label>New Zealand</label>"
                +"<value>newzealand</value>"
              +"</item>"
              +"<item id=\"Italy\" parent=\"europe\">"
                +"<label>Italy</label>"
                +"<value>Italy</value>"
              +"</item>"
              +"<item id=\"england\" parent=\"europe\">"
                +"<label>England</label>"
                +"<value>england</value>"
              +"</item>"
              +"<item id=\"netherlands\" parent=\"europe\">"
                +"<label>Netherlands</label>"
                +"<value>netherlands</value>"
              +"</item>"
              +"<item id=\"sweden\" parent=\"europe\">"
                +"<label>Sweden</label>"
                +"<value>sweden</value>"
              +"</item>"
            +"</dynamiclist>"
          +"</xf:instance>"
          +"<xf:instance id=\"district\">"
            +"<dynamiclist>"
              +"<item id=\"kampala\" parent=\"uganda\">"
                +"<label>Kampala</label>"
                +"<value>kampala</value>"
              +"</item>"
              +"<item id=\"masaka\" parent=\"uganda\">"
                +"<label>Masaka</label>"
                +"<value>masaka</value>"
              +"</item>"
              +"<item id=\"mbale\" parent=\"uganda\">"
                +"<label>Mbale</label>"
                +"<value>mbale</value>"
              +"</item>"
              +"<item id=\"mbarara\" parent=\"uganda\">"
                +"<label>Mbarara</label>"
                +"<value>mbarara</value>"
              +"</item>"
              +"<item id=\"nairobi\" parent=\"kenya\">"
                +"<label>Nairobi</label>"
                +"<value>nairobi</value>"
              +"</item>"
              +"<item id=\"kisumu\" parent=\"kenya\">"
                +"<label>Kisumu</label>"
                +"<value>kisumu</value>"
              +"</item>"
              +"<item id=\"eldoret\" parent=\"kenya\">"
                +"<label>Eldoret</label>"
                +"<value>eldoret</value>"
              +"</item>"
            +"</dynamiclist>"
          +"</xf:instance>"
          +"<xf:instance id=\"village\">"
            +"<dynamiclist>"
              +"<item id=\"kawempe\" parent=\"kampala\">"
                +"<label>Kawempe</label>"
                +"<value>kawempe</value>"
              +"</item>"
              +"<item id=\"kuvulu\" parent=\"kampala\">"
                +"<label>Kivulu</label>"
                +"<value>kuvulu</value>"
              +"</item>"
              +"<item id=\"kisenyi\" parent=\"kampala\">"
                +"<label>Kisenyi</label>"
                +"<value>kisenyi</value>"
              +"</item>"
              +"<item id=\"najjanankumbi\" parent=\"kampala\">"
                +"<label>Najjanankumbi</label>"
                +"<value>najjanankumbi</value>"
              +"</item>"
              +"<item id=\"kamugombwa\" parent=\"masaka\">"
                +"<label>Kamugombwa</label>"
                +"<value>kamugombwa</value>"
              +"</item>"
              +"<item id=\"nabinene\" parent=\"masaka\">"
                +"<label>Nabinene</label>"
                +"<value>nabinene</value>"
              +"</item>"
              +"<item id=\"nyendo\" parent=\"masaka\">"
                +"<label>Nyendo</label>"
                +"<value>nyendo</value>"
              +"</item>"
            +"</dynamiclist>"
          +"</xf:instance>"
          +"<xf:bind id=\"patientid\" nodeset=\"/patientreg/patientid\" type=\"xsd:string\"/>"
          +"<xf:bind id=\"title\" nodeset=\"/patientreg/title\" type=\"xsd:string\"/>"
          +"<xf:bind id=\"firstname\" nodeset=\"/patientreg/firstname\" type=\"xsd:string\"/>"
          +"<xf:bind id=\"lastname\" nodeset=\"/patientreg/lastname\" type=\"xsd:string\" required=\"true()\"/>"
          +"<xf:bind id=\"sex\" nodeset=\"/patientreg/sex\" type=\"xsd:string\"/>"
          +"<xf:bind id=\"birthdate\" nodeset=\"/patientreg/birthdate\" type=\"xsd:date\" constraint=\". &lt;= 'today()'\" message=\"Cannot be greater than today\"/>"
          +"<xf:bind id=\"weight\" nodeset=\"/patientreg/weight\" type=\"xsd:decimal\" constraint=\". &gt;= 0 and . &lt;= 200\" message=\"Should be between 0 and 200 inclusive\"/>"
          +"<xf:bind id=\"height\" nodeset=\"/patientreg/height\" type=\"xsd:int\" constraint=\". &gt;= 1 and . &gt;= 20\" message=\"Should be between 1 and 20 inclusive\"/>"
          +"<xf:bind id=\"pregnant\" nodeset=\"/patientreg/pregnant\" type=\"xsd:boolean\" relevant=\"/patientreg/sex = 'female'\" action=\"enable\" readonly=\"true()\" required=\"false()\"/>"
          +"<xf:bind id=\"arvs\" nodeset=\"/patientreg/arvs\" type=\"xsd:string\"/>"
          +"<xf:bind id=\"picture\" nodeset=\"/patientreg/picture\" format=\"image\" type=\"xsd:base64Binary\"/>"
          +"<xf:bind id=\"coughsound\" nodeset=\"/patientreg/coughsound\" type=\"xsd:base64Binary\" format=\"audio\"/>"
          +"<xf:bind id=\"recordvideo\" nodeset=\"/patientreg/recordvideo\" format=\"video\" type=\"xsd:base64Binary\"/>"
          +"<xf:bind id=\"continent\" nodeset=\"/patientreg/continent\" type=\"xsd:string\"/>"
          +"<xf:bind id=\"country\" nodeset=\"/patientreg/country\" type=\"xsd:string\"/>"
          +"<xf:bind id=\"district\" nodeset=\"/patientreg/district\" type=\"xsd:string\"/>"
          +"<xf:bind id=\"village\" nodeset=\"/patientreg/village\" type=\"xsd:string\"/>"
          +"<xf:bind id=\"nokids\" nodeset=\"/patientreg/nokids\" type=\"xsd:int\" constraint=\". &gt;= 0 and . &lt; 100\" message=\"Should be between 0 and 100\"/>"
          +"<xf:bind id=\"kid\" nodeset=\"/patientreg/kids/kid\" relevant=\"/patientreg/nokids &gt; 0\" action=\"enable\" constraint=\"count(.) = patientreg/nokids\" message=\"Kid rows should be equal to the number of kids\" readonly=\"true()\" required=\"false()\"/>"
          +"<xf:bind id=\"starttime\" nodeset=\"/patientreg/starttime\" type=\"xsd:time\"/>"
          +"<xf:bind id=\"endtime\" nodeset=\"/patientreg/endtime\" type=\"xsd:time\"/>"
        +"</xf:model>"
        +"<xf:group id=\"1\">"
          +"<xf:label>Page1</xf:label>"
          +"<xf:input bind=\"patientid\">"
            +"<xf:label>Patient ID</xf:label>"
          +"</xf:input>"
          +"<xf:select1 bind=\"title\">"
            +"<xf:label>Title</xf:label>"
            +"<xf:item id=\"mr\">"
              +"<xf:label>Mr</xf:label>"
              +"<xf:value>mr</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"mrs\">"
              +"<xf:label>Mrs</xf:label>"
              +"<xf:value>mrs</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"miss\">"
              +"<xf:label>Miss</xf:label>"
              +"<xf:value>miss</xf:value>"
            +"</xf:item>"
          +"</xf:select1>"
          +"<xf:input bind=\"firstname\">"
            +"<xf:label>First Name</xf:label>"
          +"</xf:input>"
          +"<xf:input bind=\"lastname\">"
            +"<xf:label>Last Name</xf:label>"
          +"</xf:input>"
          +"<xf:select1 bind=\"sex\">"
            +"<xf:label>Sex</xf:label>"
            +"<xf:item id=\"male\">"
              +"<xf:label>Male</xf:label>"
              +"<xf:value>male</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"female\">"
              +"<xf:label>Female</xf:label>"
              +"<xf:value>female</xf:value>"
            +"</xf:item>"
          +"</xf:select1>"
          +"<xf:input bind=\"birthdate\">"
            +"<xf:label>Birth Date</xf:label>"
          +"</xf:input>"
          +"<xf:input bind=\"weight\">"
            +"<xf:label>Weight (Kg)</xf:label>"
          +"</xf:input>"
          +"<xf:input bind=\"height\">"
            +"<xf:label>Height (ft)</xf:label>"
          +"</xf:input>"
          +"<xf:input bind=\"pregnant\">"
            +"<xf:label>Is ${/patientreg/lastname}$ pregnant?</xf:label>"
          +"</xf:input>"
          +"<xf:select bind=\"arvs\">"
            +"<xf:label>ARVS</xf:label>"
            +"<xf:item id=\"azt\">"
              +"<xf:label>AZT</xf:label>"
              +"<xf:value>azt</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"abicavir\">"
              +"<xf:label>ABICAVIR</xf:label>"
              +"<xf:value>abicavir</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"efivarence\">"
              +"<xf:label>EFIVARENCE</xf:label>"
              +"<xf:value>efivarence</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"triomune\">"
              +"<xf:label>TRIOMUNE</xf:label>"
              +"<xf:value>triomune</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"truvada\">"
              +"<xf:label>TRUVADA</xf:label>"
              +"<xf:value>truvada</xf:value>"
            +"</xf:item>"
          +"</xf:select>"
          +"<xf:input bind=\"picture\">"
            +"<xf:label>Picture</xf:label>"
          +"</xf:input>"
          +"<xf:input bind=\"coughsound\">"
            +"<xf:label>Cough Sound</xf:label>"
          +"</xf:input>"
          +"<xf:input bind=\"recordvideo\">"
            +"<xf:label>Record Video</xf:label>"
          +"</xf:input>"
          +"<xf:select1 bind=\"continent\">"
            +"<xf:label>Continent</xf:label>"
            +"<xf:item id=\"africa\">"
              +"<xf:label>Africa</xf:label>"
              +"<xf:value>africa</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"asia\">"
              +"<xf:label>Asia</xf:label>"
              +"<xf:value>asia</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"europe\">"
              +"<xf:label>Europe</xf:label>"
              +"<xf:value>europe</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"southamerica\">"
              +"<xf:label>South America</xf:label>"
              +"<xf:value>southamerica</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"northamerica\">"
              +"<xf:label>North America</xf:label>"
              +"<xf:value>northamerica</xf:value>"
            +"</xf:item>"
            +"<xf:item id=\"australia\">"
              +"<xf:label>Australia</xf:label>"
              +"<xf:value>australia</xf:value>"
            +"</xf:item>"
          +"</xf:select1>"
          +"<xf:select1 bind=\"country\">"
            +"<xf:label>Country</xf:label>"
            +"<xf:itemset nodeset=\"instance('country')/item[@parent=instance('patientreg')/continent]\">"
              +"<xf:label ref=\"label\"/>"
              +"<xf:value ref=\"value\"/>"
            +"</xf:itemset>"
          +"</xf:select1>"
          +"<xf:select1 bind=\"district\">"
            +"<xf:label>District</xf:label>"
            +"<xf:itemset nodeset=\"instance('district')/item[@parent=instance('patientreg')/country]\">"
              +"<xf:label ref=\"label\"/>"
              +"<xf:value ref=\"value\"/>"
            +"</xf:itemset>"
          +"</xf:select1>"
          +"<xf:select1 bind=\"village\">"
            +"<xf:label>Village</xf:label>"
            +"<xf:itemset nodeset=\"instance('village')/item[@parent=instance('patientreg')/district]\">"
              +"<xf:label ref=\"label\"/>"
              +"<xf:value ref=\"value\"/>"
            +"</xf:itemset>"
          +"</xf:select1>"
          +"<xf:input bind=\"nokids\">"
            +"<xf:label># of kids</xf:label>"
          +"</xf:input>"
          +"<xf:group id=\"kids/kid\">"
            +"<xf:label>Kids</xf:label>"
            +"<xf:repeat bind=\"kid\">"
              +"<xf:input ref=\"kidname\" type=\"xsd:string\">"
                +"<xf:label>Name</xf:label>"
              +"</xf:input>"
              +"<xf:select1 ref=\"kidsex\" type=\"xsd:string\">"
                +"<xf:label>Sex</xf:label>"
                +"<xf:item id=\"male\">"
                  +"<xf:label>Male</xf:label>"
                  +"<xf:value>male</xf:value>"
                +"</xf:item>"
                +"<xf:item id=\"female\">"
                  +"<xf:label>Female</xf:label>"
                  +"<xf:value>female</xf:value>"
                +"</xf:item>"
              +"</xf:select1>"
              +"<xf:input ref=\"kidage\" type=\"xsd:int\">"
                +"<xf:label>Age</xf:label>"
              +"</xf:input>"
            +"</xf:repeat>"
          +"</xf:group>"
          +"<xf:input bind=\"starttime\">"
            +"<xf:label>Start Time</xf:label>"
          +"</xf:input>"
          +"<xf:input bind=\"endtime\">"
            +"<xf:label>End Time</xf:label>"
          +"</xf:input>"
        +"</xf:group>"
      +"</xf:xforms>";
    }
    
    
    public static String getFormList(){
    	return "<forms> " +
			"<form url=\"http://open-data-kit.appspot.com/formXml?odkFormKey=ag1vcGVuLWRhdGEta2l0chELEgRGb3JtIgdERUZBVUxUDA\">Sample form</form> "+
			"<form url=\"http://open-data-kit.appspot.com/formXml?odkFormKey=ag1vcGVuLWRhdGEta2l0choLEgRGb3JtIhBIb3VzZWhvbGRTdXJ2ZXkxDA\">Household Survey</form> "+
			"<form url=\"http://open-data-kit.appspot.com/formXml?odkFormKey=ag1vcGVuLWRhdGEta2l0cg8LEgRGb3JtIgViYXNpYww\">Basic</form> "+
			"<form url=\"http://open-data-kit.appspot.com/formXml?odkFormKey=ag1vcGVuLWRhdGEta2l0chQLEgRGb3JtIgpnZW9fdGFnZ2VyDA\">Geo Tagger</form> "+
			"<form url=\"http://open-data-kit.appspot.com/formXml?odkFormKey=ag1vcGVuLWRhdGEta2l0cg4LEgRGb3JtIgRpbWNpDA\">IMCI</form>"+
			"<form url=\"http://open-data-kit.appspot.com/formXml?odkFormKey=ag1vcGVuLWRhdGEta2l0chELEgRGb3JtIgd3aWRnZXRzDA\">Widgets</form> "+
			"</forms>";
    }
    
    
    public static String getFormList2(){
    	String xml = "<?xml version='1.0' encoding='UTF-8' ?>";
		xml += "\n<xforms>";

		xml += "\n  <xform id='1' name='Form 1' />";
		xml += "\n  <xform id='2' name='Form 2' />";
		xml += "\n  <xform id='3' name='Form 3' />";
		xml += "\n  <xform id='4' name='Form 4' />";
		xml += "\n  <xform id='5' name='Form 5' />";
		xml += "\n  <xform id='6' name='Form 6' />";
		xml += "\n  <xform id='7' name='Form 7' />";
		xml += "\n  <xform id='8' name='Form 8' />";
		xml += "\n  <xform id='9' name='Pregnancy Registration Form 9' />";
		xml += "\n  <xform id='10' name='Form 10' />";
		xml += "\n  <xform id='11' name='Form 11' />";
		xml += "\n  <xform id='12' name='Form 12' />";
		xml += "\n  <xform id='13' name='Form 13' />";
		xml += "\n  <xform id='14' name='Form 14' />";
		xml += "\n  <xform id='15' name='Form 15' />";

		xml += "\n</xforms>";
		
		return xml;
    }
}
