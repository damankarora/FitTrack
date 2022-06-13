import * as React from "react"
import '../App.css'

const SvgComponent = (props) => (
  <svg width={40} height={40} {...props} className="rotate">
    <circle
      cx={22}
      cy={24}
      r={15}
      fill="#fff"
      stroke="#e6e6e6"
      strokeWidth={2}
    />
    <circle
      className="percent sixty"
      cx={22}
      cy={24}
      r={15}
      fill="none"
      stroke={props.stroke}
      strokeWidth={2}
      pathLength={100}
    />
  </svg>
)

export default SvgComponent
