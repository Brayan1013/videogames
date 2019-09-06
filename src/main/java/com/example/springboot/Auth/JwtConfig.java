package com.example.springboot.Auth;

public class JwtConfig {
	
	public static final String SECRET = "llave.privada.12345";
	
	public static final String PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEAqCTZLDU+Lw966+Xu0QL3q9+LtgkfxG1ExSR1HHe6wp7OMNPb\r\n" + 
			"5niCh+6YyHIlgAz9d24Ax4l4ivFbjAXE4b/E9GbxSkRIj2+G11t+2U7skyUu/kdR\r\n" + 
			"U+KdG5dzEPiMGoobuH5NthDkZ7aSpOFB44aA/6rGb28CQidbln072pVW71Gqidgj\r\n" + 
			"DKOYDxRyzGNK1LlB2QnrsMA0UfVwFJOuoVrj7ZOx7jRVYlzxV5Vkxnz7v0LUdmyU\r\n" + 
			"3zkLEbHDncplBGSnJ5vq3sq57VjuZcq2ARC/41jYkvZhYslsqRPOROaLh5xD1NDF\r\n" + 
			"VMSbZte7n8cd5SqPX54fRJ4G9yaikdweq6BbmwIDAQABAoIBAQCmZZDlxur6937V\r\n" + 
			"5H4qKZ2gYWGJjA37/YaI8kNTIMmZwD6c44xC0A+dJencs9mYl0Je5mWvEcRQ+/l0\r\n" + 
			"4GCuZ3EpzJYMwPZtvBjdFpa5xvknxSx0R2+cbC6+F2qv6Mf9m59DPPr6qkzetjb5\r\n" + 
			"JFJoL7uJSB+IN3ssrHZevyB0eEXIHpaht80XGSqFWajIbfqGkhdW0LsFr93rLszF\r\n" + 
			"wDK228neIk83d6wWILr5nbYwFwqHcY3x2ttilPcqLNTZP4YO893DrxBqzFF8IZIR\r\n" + 
			"Rx8FWqTnZvMJFPedcEDydFPoTOt30reIXuI4awp+zp+yLktmQPhgCk2bHWEQRgIy\r\n" + 
			"tUdAH/MBAoGBANZoC/P4uLzxqJ+Yzrt3zoAEK7D0/nNPputwXDsf+ROK9ZRIVB/k\r\n" + 
			"fKnqlaY9jKpjkC1Oclmr8TYjMK86cTB2or1wMqOnL7Hb1XxZrQTz89OPKJZUDffp\r\n" + 
			"5d6VTRbbRH6GhVIcdtUR5S9I9sVGGsbrHw//uuxML2BKHLeb8LY4Na0rAoGBAMjD\r\n" + 
			"Srhhu0sohSBGN0yvQNiYbZ3EZdEyQSk0yoGn6ah+vKFZ75rVQ5YbpvrznnVMrq86\r\n" + 
			"AVDHKEdfWNJuEU5DkvA5JQ3lfCnKpCKuc7U7vyDt3JmW4hf+DWbY3PQeQP9wUDJx\r\n" + 
			"Lno6CrQra9CsyyMz+P+bftrIxRAHOmlMyq+S7zNRAoGAUoLmHrTYO3Zwo0LGRGT9\r\n" + 
			"5kw0mDwgdDXyEedCx0otq7Stf6w6G6/tveJWRwymxqfcQ2NOJG5EPMZcJQNDrhfp\r\n" + 
			"1yeHhW2AsFzekqVcLWzIp+EPCJM+cOrlmLwyV5p4iIyyYAAuRx9E6OaWeSLaiTTQ\r\n" + 
			"1nJe+DL1hIPg+1Qa1bVaCtMCgYBuIvzt0+e83pjR0NNrhUVMzaps/55funMW2Pgz\r\n" + 
			"UtuTbRusZLtWtKJkSf079MQ8U3oNdQPdvbQyG3ON1YY26klE8gSK8BdCkgGjaRLf\r\n" + 
			"HMzIktUPiT+1q9DMWDI21hYQTBwi4AaAVTEkECIQk4rJYCy8hSB9g+Df3ydDNzaG\r\n" + 
			"RVRmUQKBgQC4C6JFE4i3O9VS1wLEPw5v5jmVOfqxFQ1lz1xa3Vp6BHK5EGm/AbTg\r\n" + 
			"Ed/Q2AB/apVQ0xrfsC2nBdwB1MqyAXIczaQLoDFzvXGTBkE95FO0ds3M3oImiwtZ\r\n" + 
			"eEG4hF5CchW4yvQmVOVIDIO9xAqALTv0scu+jVwi3DONjoyVVevgsg==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqCTZLDU+Lw966+Xu0QL3\r\n" + 
			"q9+LtgkfxG1ExSR1HHe6wp7OMNPb5niCh+6YyHIlgAz9d24Ax4l4ivFbjAXE4b/E\r\n" + 
			"9GbxSkRIj2+G11t+2U7skyUu/kdRU+KdG5dzEPiMGoobuH5NthDkZ7aSpOFB44aA\r\n" + 
			"/6rGb28CQidbln072pVW71GqidgjDKOYDxRyzGNK1LlB2QnrsMA0UfVwFJOuoVrj\r\n" + 
			"7ZOx7jRVYlzxV5Vkxnz7v0LUdmyU3zkLEbHDncplBGSnJ5vq3sq57VjuZcq2ARC/\r\n" + 
			"41jYkvZhYslsqRPOROaLh5xD1NDFVMSbZte7n8cd5SqPX54fRJ4G9yaikdweq6Bb\r\n" + 
			"mwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

}
