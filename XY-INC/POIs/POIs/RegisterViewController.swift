import UIKit
import Alamofire

class RegisterViewController: UIViewController {
    
    @IBOutlet var txtY: UITextField!
    @IBOutlet var txtName: UITextField!
    @IBOutlet var txtX: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func buttonRegister(sender: AnyObject) {
        if txtX.text == "" {
            let alertx = UIAlertController(title: "ERRO", message: "coordenada x inválida ou não informada.", preferredStyle: UIAlertControllerStyle.Alert)
            alertx.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.Default, handler: nil))
            self.presentViewController(alertx, animated: true, completion: nil)
        } else if txtY.text == "" {
            let alerty = UIAlertController(title: "ERRO", message: "coordenada y inválida ou não informada.", preferredStyle: UIAlertControllerStyle.Alert)
            alerty.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.Default, handler: nil))
            self.presentViewController(alerty, animated: true, completion: nil)
        } else if txtName.text == "" {
            let alertname = UIAlertController(title: "ERRO", message: "Nome inválido ou não informado.", preferredStyle: UIAlertControllerStyle.Alert)
            alertname.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.Default, handler: nil))
            self.presentViewController(alertname, animated: true, completion: nil)
        } else {
            
            let jsonPOI = [
                "x": txtX.text!,
                "y": txtY.text!,
                "nome": txtName.text!
            ]
            
            Alamofire.request(.POST, "http://192.168.0.12:8081/pois", parameters: jsonPOI, encoding: .JSON, headers: ["Content-Type" : "application/json"]).responseJSON { response in
                
                if response.response?.statusCode == 200 {
                    
                    if response.data != nil {
                        do {
                            
                            let json =  try NSJSONSerialization.JSONObjectWithData(response.data!, options: [])
                            
                            let msg = json["message"] as! String
                            
                            let alertx = UIAlertController(title: "", message: msg, preferredStyle: UIAlertControllerStyle.Alert)
                            alertx.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.Default, handler: nil))
                            self.presentViewController(alertx, animated: true, completion: nil)
                            
                        } catch {
                            let alertx = UIAlertController(title: "ERRO", message: "Um erro ocorreu! tente novamente.", preferredStyle: UIAlertControllerStyle.Alert)
                            alertx.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.Default, handler: nil))
                            self.presentViewController(alertx, animated: true, completion: nil)
                        }
                        
                    } else {
                        let alertx = UIAlertController(title: "ERRO", message: "Um erro ocorreu! tente novamente.", preferredStyle: UIAlertControllerStyle.Alert)
                        alertx.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.Default, handler: nil))
                        self.presentViewController(alertx, animated: true, completion: nil)
                    }
                }
                
            }
        }
    }
}
