import * as React from "react"

const SvgComponent = (props) => (
  <svg width={80} height={80} {...props}>
    <circle
      cx={60}
      cy={60}
      r={15}
      fill="#fff"
      stroke="#e6e6e6"
      strokeWidth={2}
    />
    <circle
      className="percent ninety"
      cx={60}
      cy={60}
      r={15}
      fill="none"
      stroke="#f77a52"
      strokeWidth={2}
      pathLength={100}
    />
  </svg>
)

export default SvgComponent
