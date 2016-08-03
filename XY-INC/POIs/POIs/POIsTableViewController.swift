import UIKit
import Alamofire

class POIsTableViewController: UITableViewController {
    
    var listPoi = [POI]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        Alamofire.request(.GET, "http://192.168.0.12:8081/pois")
            .responseJSON { response in
                
                if (response.response?.statusCode == 200) {
                    
                    
                    if response.data != nil {
                        do {
                            
                           let json =  try NSJSONSerialization.JSONObjectWithData(response.data!, options: [])
                            
                            
                            for jsonPOI in (json as! [NSDictionary]) {
                                let poi:POI = POI()
                                poi.nome = jsonPOI["nome"] as? String
                                poi.x = jsonPOI["x"]! as! Int
                                poi.y = jsonPOI["y"]! as! Int
                               self.listPoi.append(poi)
                            }
                            self.tableView.reloadData()
                            
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
                    
                } else {
                    let alertx = UIAlertController(title: "ERRO", message: "Um erro ocorreu! tente novamente.", preferredStyle: UIAlertControllerStyle.Alert)
                    alertx.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.Default, handler: nil))
                    self.presentViewController(alertx, animated: true, completion: nil)
                }
        }
    }
    
    
    // MARK: - Table view data source
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return listPoi.count
    }
    
    override func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
        return 70.0
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell: POITableViewCell = tableView.dequeueReusableCellWithIdentifier("cellPOI", forIndexPath: indexPath) as! POITableViewCell
        
        cell.lblName.text = listPoi[indexPath.row].nome
        cell.lblX.text = String(listPoi[indexPath.row].x)
        cell.lblY.text = String(listPoi[indexPath.row].y)
        
        
        return cell
    }
    
}
